package com.alphawallet.app.util.boc;

import org.apache.oltu.oauth2.client.HttpClient;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthClientResponse;
import org.apache.oltu.oauth2.client.response.OAuthClientResponseFactory;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OAuthOkHttpClient implements HttpClient {
    private OkHttpClient client;

    public OAuthOkHttpClient() {
        this.client = new OkHttpClient();
    }

    public OAuthOkHttpClient(OkHttpClient client) {
        this.client = client;
    }

    public <T extends OAuthClientResponse> T execute(OAuthClientRequest request, Map<String, String> headers, String requestMethod, Class<T> responseClass) throws OAuthSystemException, OAuthProblemException {
        MediaType mediaType = MediaType.parse("application/json");
        Request.Builder requestBuilder = (new Request.Builder()).url(request.getLocationUri());
        if (headers != null) {
            Iterator var7 = headers.entrySet().iterator();

            while(var7.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var7.next();
                if (((String)entry.getKey()).equalsIgnoreCase("Content-Type")) {
                    mediaType = MediaType.parse((String)entry.getValue());
                } else {
                    requestBuilder.addHeader((String)entry.getKey(), (String)entry.getValue());
                }
            }
        }

        RequestBody body = request.getBody() != null ? RequestBody.create(mediaType, request.getBody()) : null;
        requestBuilder.method(requestMethod, body);

        try {
            Response response = this.client.newCall(requestBuilder.build()).execute();
            return OAuthClientResponseFactory.createCustomResponse(response.body().string(), response.body().contentType().toString(), response.code(), responseClass);
        } catch (IOException var9) {
            throw new OAuthSystemException(var9);
        }
    }

    public void shutdown() {
    }
}
