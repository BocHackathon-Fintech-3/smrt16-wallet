package com.alphawallet.app.util.boc;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AccountsApi {
    @GET("v1/accounts/{accountId}")
    Call<List<Account>> getAccountDetails(@Path("accountId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("subscriptionId") String var6, @Header("Authorization") String var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("onlineAccessFlag") Boolean var14, @Header("lang") String var15);

    @GET("v1/accounts/{accountId}/statement")
    Call<Statement> getAccountStatement(@Path("accountId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("subscriptionId") String var6, @Header("Authorization") String var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("lang") String var14, @Query("startDate") String var15, @Query("endDate") String var16, @Query("maxCount") BigDecimal var17);

    @GET("v1/accounts")
    Call<List<Account>> getAccounts(@Header("Authorization") String var1, @Header("originUserId") String var2, @Header("journeyId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("subscriptionId") String var6, @Header("customerId") String var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("lang") String var14, @Header("onlineAccessFlag") Boolean var15);

    @GET("v1/accounts/{accountId}/balance")
    Call<List<Account>> getAvailableBalance(@Path("accountId") String var1, @Header("journeyId") String var2, @Header("originUserId") String var3, @Header("timeStamp") String var4, @Header("tppId") String var5, @Header("subscriptionId") String var6, @Header("Authorization") String var7, @Header("originSourceId") String var8, @Header("originChannelId") String var9, @Header("originDeptId") String var10, @Header("originEmployeeId") String var11, @Header("originTerminalId") String var12, @Header("correlationId") String var13, @Header("lang") String var14);
}
