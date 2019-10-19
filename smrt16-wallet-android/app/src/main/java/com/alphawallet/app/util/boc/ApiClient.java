package com.alphawallet.app.util.boc;

import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ApiClient implements ApiConfiguration {
    private Map<String, Interceptor> apiAuthorizations;
    private OkHttpClient.Builder okBuilder;
    private retrofit2.Retrofit.Builder adapterBuilder;
    private JSON json;
    private String apiUrl;

    public ApiClient() {
        this.apiUrl = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2";
        this.apiAuthorizations = new LinkedHashMap();
        this.createDefaultAdapter();
    }

    public ApiClient(String[] authNames) {
        this();
        String[] var2 = authNames;
        int var3 = authNames.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            String authName = var2[var4];
            Object auth;
            if ("Application_oauth".equals(authName)) {
                auth = new OAuth(OAuthFlow.application, "", "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/oauth2/token", "TPPOAuth2Security");
            } else if ("Client_id".equals(authName)) {
                auth = new ApiKeyAuth("query", "client_id");
                ((ApiKeyAuth)auth).setApiKey("7c7bcb8f-7930-495d-adc2-e69f1afb07da");
            } else if ("Client_secret".equals(authName)) {
                auth = new ApiKeyAuth("query", "client_secret");
                ((ApiKeyAuth)auth).setApiKey("pS0qV2iG4pA7dM6aE3xG4xX1gJ3rK8eX1hU3jY7uH3hP3wS2aO");
            } else if ("clientIdHeader".equals(authName)) {
                auth = new ApiKeyAuth("header", "X-IBM-Client-Id");
            } else {
                if (!"clientSecretHeader".equals(authName)) {
                    throw new RuntimeException("auth name \"" + authName + "\" not found in available auth names");
                }

                auth = new ApiKeyAuth("header", "X-IBM-Client-Secret");
            }

            this.addAuthorization(authName, (Interceptor)auth);
        }

        this.configureAuthorizationFlow("7c7bcb8f-7930-495d-adc2-e69f1afb07da", "pS0qV2iG4pA7dM6aE3xG4xX1gJ3rK8eX1hU3jY7uH3hP3wS2aO", "");
    }

    public ApiClient(int identifier, String url) {
        this.apiUrl = url;
        this.apiAuthorizations = new LinkedHashMap();
        this.createDefaultAdapter();
    }

    public ApiClient(String authName) {
        this(new String[]{authName});
    }

    public ApiClient(String authName, String apiKey) {
        this(authName);
        this.setApiKey(apiKey);
    }

    public ApiClient(String authName, String username, String password) {
        this(authName);
        this.setCredentials(username, password);
    }

    public ApiClient(String authName, String clientId, String secret, String username, String password) {
        this(authName);
        this.getTokenEndPoint().setClientId(clientId).setClientSecret(secret).setUsername(username).setPassword(password);
    }

    public void createDefaultAdapter() {
        this.json = new JSON();
        this.okBuilder = new OkHttpClient.Builder();
        String baseUrl = this.apiUrl;
        if (!baseUrl.endsWith("/")) {
            baseUrl = baseUrl + "/";
        }

        this.adapterBuilder = (new retrofit2.Retrofit.Builder()).baseUrl(baseUrl).addConverterFactory(ScalarsConverterFactory.create()).addConverterFactory(GsonCustomConverterFactory.create(this.json.getGson()));
    }

    public <S> S createService(Class<S> serviceClass) {
        return this.adapterBuilder.client(this.okBuilder.build()).build().create(serviceClass);
    }

    public ApiClient setDateFormat(DateFormat dateFormat) {
        this.json.setDateFormat(dateFormat);
        return this;
    }

    public ApiClient setSqlDateFormat(DateFormat dateFormat) {
        this.json.setSqlDateFormat(dateFormat);
        return this;
    }

    public ApiClient setOffsetDateTimeFormat(DateTimeFormatter dateFormat) {
        this.json.setOffsetDateTimeFormat(dateFormat);
        return this;
    }

    public ApiClient setLocalDateFormat(DateTimeFormatter dateFormat) {
        this.json.setLocalDateFormat(dateFormat);
        return this;
    }

    public ApiClient setApiKey(String apiKey) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var2.hasNext()) {
                return this;
            }

            apiAuthorization = (Interceptor)var2.next();
        } while(!(apiAuthorization instanceof ApiKeyAuth));

        ApiKeyAuth keyAuth = (ApiKeyAuth)apiAuthorization;
        keyAuth.setApiKey(apiKey);
        return this;
    }

    public ApiClient setCredentials(String username, String password) {
        Iterator var3 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var3.hasNext()) {
                return this;
            }

            apiAuthorization = (Interceptor)var3.next();
            if (apiAuthorization instanceof HttpBasicAuth) {
                HttpBasicAuth basicAuth = (HttpBasicAuth)apiAuthorization;
                basicAuth.setCredentials(username, password);
                return this;
            }
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.getTokenRequestBuilder().setUsername(username).setPassword(password);
        return this;
    }

    public OAuthClientRequest.TokenRequestBuilder getTokenEndPoint() {
        Iterator var1 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            apiAuthorization = (Interceptor)var1.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        return oauth.getTokenRequestBuilder();
    }

    public OAuthClientRequest.AuthenticationRequestBuilder getAuthorizationEndPoint() {
        Iterator var1 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var1.hasNext()) {
                return null;
            }

            apiAuthorization = (Interceptor)var1.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        return oauth.getAuthenticationRequestBuilder();
    }

    public ApiClient setAccessToken(String accessToken) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var2.hasNext()) {
                return this;
            }

            apiAuthorization = (Interceptor)var2.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.setAccessToken(accessToken);
        return this;
    }

    public ApiClient configureAuthorizationFlow(String clientId, String clientSecret, String redirectURI) {
        Iterator var4 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var4.hasNext()) {
                return this;
            }

            apiAuthorization = (Interceptor)var4.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.getTokenRequestBuilder().setClientId(clientId).setClientSecret(clientSecret).setRedirectURI(redirectURI);
        oauth.getAuthenticationRequestBuilder().setClientId(clientId).setRedirectURI(redirectURI);
        return this;
    }

    public ApiClient registerAccessTokenListener(OAuth.AccessTokenListener accessTokenListener) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        Interceptor apiAuthorization;
        do {
            if (!var2.hasNext()) {
                return this;
            }

            apiAuthorization = (Interceptor)var2.next();
        } while(!(apiAuthorization instanceof OAuth));

        OAuth oauth = (OAuth)apiAuthorization;
        oauth.registerAccessTokenListener(accessTokenListener);
        return this;
    }

    public ApiClient addAuthorization(String authName, Interceptor authorization) {
        if (this.apiAuthorizations.containsKey(authName)) {
            throw new RuntimeException("auth name \"" + authName + "\" already in api authorizations");
        } else {
            this.apiAuthorizations.put(authName, authorization);
            this.okBuilder.addInterceptor(authorization);
            return this;
        }
    }

    public Map<String, Interceptor> getApiAuthorizations() {
        return this.apiAuthorizations;
    }

    public ApiClient setApiAuthorizations(Map<String, Interceptor> apiAuthorizations) {
        this.apiAuthorizations = apiAuthorizations;
        return this;
    }

    public retrofit2.Retrofit.Builder getAdapterBuilder() {
        return this.adapterBuilder;
    }

    public ApiClient setAdapterBuilder(retrofit2.Retrofit.Builder adapterBuilder) {
        this.adapterBuilder = adapterBuilder;
        return this;
    }

    public OkHttpClient.Builder getOkBuilder() {
        return this.okBuilder;
    }

    public void addAuthsToOkBuilder(OkHttpClient.Builder okBuilder) {
        Iterator var2 = this.apiAuthorizations.values().iterator();

        while(var2.hasNext()) {
            Interceptor apiAuthorization = (Interceptor)var2.next();
            okBuilder.addInterceptor(apiAuthorization);
        }

    }

    public void configureFromOkclient(OkHttpClient okClient) {
        this.okBuilder = okClient.newBuilder();
        this.addAuthsToOkBuilder(this.okBuilder);
    }
}
