package com.alphawallet.app.util.boc;

public interface ApiConfiguration {
    String API_BASE_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2";
    String API_BASE_SIGN_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/";
    String API_TOKEN_URL = "https://sandbox-apis.bankofcyprus.com/df-boc-org-sb/sb/psd2/oauth2/token";
    String JSON_CONTENT_TYPE = "application/json";
    String CLIENT_SECRET = "pS0qV2iG4pA7dM6aE3xG4xX1gJ3rK8eX1hU3jY7uH3hP3wS2aO";
    String CLIENT_ID = "7c7bcb8f-7930-495d-adc2-e69f1afb07da";
    String GRANT_TYPE_CREDENTIALS = "client_credentials";
    String GRANT_TYPE_AUTHCODE = "authorization_code";
    String SCOPE_OAUTH2_CREDENTIALS = "TPPOAuth2Security";
    String SCOPE_OATH2_AUTHORIZATION = "UserOAuth2Security";
    String APP_NAME = "myapp";
    String TPP_ID = "singpaymentdata";
    String ORIGIN_USER_ID = "abc";
    String JOURNEY_ID = "abc";
    int AMOUNT = 999999999;
    int LIMIT = 999999999;
    String CURRENCY = "EUR";
    Boolean BALANCE = true;
    Boolean TRANASACTION_HISTORY = true;
    Boolean DETAILS = true;
    Boolean CHECK_FUNDS_AVAILABILITY = true;
    int MAX_COUNT = 2;
}

