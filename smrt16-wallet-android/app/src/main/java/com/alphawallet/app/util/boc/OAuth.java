package com.alphawallet.app.util.boc;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthJSONAccessTokenResponse;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OAuth implements Interceptor {
    private volatile String accessToken;
    private OAuthClient oauthClient;
    private OAuthClientRequest.TokenRequestBuilder tokenRequestBuilder;
    private OAuthClientRequest.AuthenticationRequestBuilder authenticationRequestBuilder;
    private OAuth.AccessTokenListener accessTokenListener;

    public OAuth(OkHttpClient client, OAuthClientRequest.TokenRequestBuilder requestBuilder) {
        this.oauthClient = new OAuthClient(new OAuthOkHttpClient(client));
        this.tokenRequestBuilder = requestBuilder;
    }

    public OAuth(OAuthClientRequest.TokenRequestBuilder requestBuilder) {
        this(new OkHttpClient(), requestBuilder);
    }

    public OAuth(OAuthFlow flow, String authorizationUrl, String tokenUrl, String scopes) {
        this(OAuthClientRequest.tokenLocation(tokenUrl).setScope(scopes));
        this.setFlow(flow);
        this.authenticationRequestBuilder = OAuthClientRequest.authorizationLocation(authorizationUrl);
    }

    public void setFlow(OAuthFlow flow) {
        switch(flow) {
            case accessCode:
            case implicit:
                this.tokenRequestBuilder.setGrantType(GrantType.AUTHORIZATION_CODE);
                break;
            case password:
                this.tokenRequestBuilder.setGrantType(GrantType.PASSWORD);
                break;
            case application:
                this.tokenRequestBuilder.setGrantType(GrantType.CLIENT_CREDENTIALS);
        }

    }

    public Response intercept(Chain chain) throws IOException {
        return this.retryingIntercept(chain, true);
    }

    private Response retryingIntercept(Chain chain, boolean updateTokenAndRetryOnAuthorizationFailure) throws IOException {
        Request request = chain.request();
        if (request.header("Authorization") != null) {
            return chain.proceed(request);
        } else {
            if (this.getAccessToken() == null) {
                this.updateAccessToken((String)null);
            }

            if (this.getAccessToken() == null) {
                return chain.proceed(chain.request());
            } else {
                Request.Builder rb = request.newBuilder();
                String requestAccessToken = new String(this.getAccessToken());

                OAuthClientRequest oAuthRequest;
                try {
                    oAuthRequest = (new OAuthBearerClientRequest(request.url().toString())).setAccessToken(requestAccessToken).buildHeaderMessage();
                } catch (OAuthSystemException var9) {
                    throw new IOException(var9);
                }

                Iterator var7 = oAuthRequest.getHeaders().entrySet().iterator();

                while(var7.hasNext()) {
                    Map.Entry<String, String> header = (Map.Entry)var7.next();
                    rb.addHeader((String)header.getKey(), (String)header.getValue());
                }

                rb.url(oAuthRequest.getLocationUri());
                Response response = chain.proceed(rb.build());
                return response != null && (response.code() == 401 || response.code() == 403) && updateTokenAndRetryOnAuthorizationFailure && this.updateAccessToken(requestAccessToken) ? this.retryingIntercept(chain, false) : response;
            }
        }
    }

    public synchronized boolean updateAccessToken(String requestAccessToken) throws IOException {
        if (this.getAccessToken() != null && !this.getAccessToken().equals(requestAccessToken)) {
            return true;
        } else {
            try {
                OAuthJSONAccessTokenResponse accessTokenResponse = this.oauthClient.accessToken(this.tokenRequestBuilder.buildBodyMessage());
                if (accessTokenResponse != null && accessTokenResponse.getAccessToken() != null) {
                    this.setAccessToken(accessTokenResponse.getAccessToken());
                    if (this.accessTokenListener != null) {
                        this.accessTokenListener.notify((BasicOAuthToken)accessTokenResponse.getOAuthToken());
                    }

                    return !this.getAccessToken().equals(requestAccessToken);
                } else {
                    return false;
                }
            } catch (OAuthSystemException var3) {
                throw new IOException(var3);
            } catch (OAuthProblemException var4) {
                throw new IOException(var4);
            }
        }
    }

    public void registerAccessTokenListener(OAuth.AccessTokenListener accessTokenListener) {
        this.accessTokenListener = accessTokenListener;
    }

    public synchronized String getAccessToken() {
        return this.accessToken;
    }

    public synchronized void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public OAuthClientRequest.TokenRequestBuilder getTokenRequestBuilder() {
        return this.tokenRequestBuilder;
    }

    public void setTokenRequestBuilder(OAuthClientRequest.TokenRequestBuilder tokenRequestBuilder) {
        this.tokenRequestBuilder = tokenRequestBuilder;
    }

    public OAuthClientRequest.AuthenticationRequestBuilder getAuthenticationRequestBuilder() {
        return this.authenticationRequestBuilder;
    }

    public void setAuthenticationRequestBuilder(OAuthClientRequest.AuthenticationRequestBuilder authenticationRequestBuilder) {
        this.authenticationRequestBuilder = authenticationRequestBuilder;
    }

    public interface AccessTokenListener {
        void notify(BasicOAuthToken var1);
    }
}
