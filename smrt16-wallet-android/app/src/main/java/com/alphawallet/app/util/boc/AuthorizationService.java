package com.alphawallet.app.util.boc;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AuthorizationService implements ApiConfiguration {
    private AuthorisationApi apiAuthorisation = (AuthorisationApi)(new ApiClient()).createService(AuthorisationApi.class);

    public AuthorizationService() {
    }

    public AccessTokenResponse getAccessToken(String clientId, String clientSecret, String grantType, String scope, String redirectUri, String refreshToken) throws IOException {
        System.out.println("GET access token");
        String code = null;
        String username = null;
        String password = null;
        Call<AccessTokenResponse> retrofitCall = this.apiAuthorisation.oauth2TokenPost(grantType, clientId, clientSecret, (String)code, redirectUri, (String)username, (String)password, scope, refreshToken);
        Response<AccessTokenResponse> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (AccessTokenResponse)response.body();
        }
    }

    public AccessTokenResponse getAccessToken2(String code, String redirectUri, String refreshToken) throws IOException {
        System.out.println("GET second access token");
        String clientId = "7c7bcb8f-7930-495d-adc2-e69f1afb07da";
        String clientSecret = "pS0qV2iG4pA7dM6aE3xG4xX1gJ3rK8eX1hU3jY7uH3hP3wS2aO";
        String scope = "UserOAuth2Security";
        String grantType = "authorization_code";
        String username = null;
        String password = null;
        System.out.println("Authorization code: " + code);
        Call<AccessTokenResponse> retrofitCall = this.apiAuthorisation.oauth2TokenPost(grantType, clientId, clientSecret, code, redirectUri, (String)username, (String)password, scope, refreshToken);
        Response<AccessTokenResponse> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (AccessTokenResponse)response.body();
        }
    }
}
