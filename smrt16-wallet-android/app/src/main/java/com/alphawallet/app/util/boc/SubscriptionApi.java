package com.alphawallet.app.util.boc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SubscriptionApi {
    @Headers({"Content-Type:application/json"})
    @POST("v1/subscriptions")
    Call<CreateSubscriptionResponse> createSubscription(@Header("journeyId") String var1, @Header("app_name") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("Authorization") String var6, @Body CreateSubscriptionRequest var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("lang") String var14);

    @GET("v1/subscriptions/accounts/{accountId}")
    Call<List<SubscriptionView>> getSubscriptionForAccount(@Path("accountId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("Authorization") String var6, @Header("subscriptionId") String var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("lang") String var14);

    @GET("v1/subscriptions/{subscriptionId}")
    Call<List<SubscriptionView>> getSubscriptionsForTPP(@Path("subscriptionId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("Authorization") String var6, @Header("originSourceId") String var7, @Header("originChannelId") String var8, @Header("originDeptId") String var9, @Header("originEmployeeId") String var10, @Header("originTerminalId") String var11, @Header("correlationId") String var12, @Header("lang") String var13);

    @DELETE("v1/subscriptions/{subscriptionId}")
    Call<Void> revokeSubscription(@Path("subscriptionId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("Authorization") String var6, @Header("originSourceId") String var7, @Header("originChannelId") String var8, @Header("originDeptId") String var9, @Header("originEmployeeId") String var10, @Header("originTerminalId") String var11, @Header("correlationId") String var12, @Header("lang") String var13);

    @Headers({"Content-Type:application/json"})
    @PATCH("v1/subscriptions/{subscriptionId}")
    Call<UpdateSubscriptionResponse> updateSubscription(@Path("subscriptionId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("Authorization") String var6, @Body UpdateSubscriptionRequest var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("lang") String var14, @Header("app_name") String var15);
}
