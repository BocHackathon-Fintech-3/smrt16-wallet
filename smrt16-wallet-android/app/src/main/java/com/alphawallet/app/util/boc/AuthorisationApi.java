package com.alphawallet.app.util.boc;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface AuthorisationApi {
    @GET("oauth2/authorize")
    Call<Void> oauth2AuthorizeGet(@Query("response_type") String var1, @Query("scope") String var2, @Query("subscriptionid") String var3, @Query("redirect_uri") String var4, @Query("state") String var5);

    @FormUrlEncoded
    @POST("oauth2/authorize")
    Call<Void> oauth2AuthorizePost(@Field("client_id") String var1, @Field("scope") String var2, @Field("resource-owner") String var3, @Field("redirect_uri") String var4, @Field("original-url") String var5, @Field("dp-state") String var6, @Field("dp-data") String var7);

    @FormUrlEncoded
    @POST("oauth2/introspect")
    Call<IntrospectResponse> oauth2IntrospectPost(@Field("token") String var1, @Field("token_type_hint") String var2);

    @FormUrlEncoded
    @POST("oauth2/token")
    Call<AccessTokenResponse> oauth2TokenPost(@Field("grant_type") String var1, @Field("client_id") String var2, @Field("client_secret") String var3, @Field("code") String var4, @Field("redirect_uri") String var5, @Field("username") String var6, @Field("password") String var7, @Field("scope") String var8, @Field("refresh_token") String var9);
}
