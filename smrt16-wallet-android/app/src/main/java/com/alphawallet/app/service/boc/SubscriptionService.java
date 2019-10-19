package com.alphawallet.app.service.boc;

import com.alphawallet.app.util.boc.ApiClient;
import com.alphawallet.app.util.boc.ApiConfiguration;
import com.alphawallet.app.util.boc.CreateSubscriptionRequest;
import com.alphawallet.app.util.boc.CreateSubscriptionResponse;
import com.alphawallet.app.util.boc.SubscriptionApi;
import com.alphawallet.app.util.boc.SubscriptionView;
import com.alphawallet.app.util.boc.UpdateSubscriptionRequest;
import com.alphawallet.app.util.boc.UpdateSubscriptionResponse;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class SubscriptionService implements ApiConfiguration {
    private SubscriptionApi apiSubscription;

    public SubscriptionService() {
        String[] auth = new String[]{"Application_oauth", "Client_id", "Client_secret"};
        this.apiSubscription = (SubscriptionApi)(new ApiClient(auth)).createService(SubscriptionApi.class);
    }

    public CreateSubscriptionResponse createSubscription(CreateSubscriptionRequest request, String journeyId, String appName, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = null;
        CreateSubscriptionRequest requestBody = new CreateSubscriptionRequest();
        requestBody.setPayments(request.getPayments());
        requestBody.setAccounts(request.getAccounts());
        Call<CreateSubscriptionResponse> retrofitCall = this.apiSubscription.createSubscription(journeyId, appName, originUserId, timeStamp, tppId, (String)authorization, requestBody, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang);
        Response<CreateSubscriptionResponse> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (CreateSubscriptionResponse)response.body();
        }
    }

    public UpdateSubscriptionResponse patchSubscription(UpdateSubscriptionRequest requestBody, String subscriptionId, String accessToken2, String journeyId, String originUserId, String tppId, String appName, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = "Bearer " + accessToken2;
        Call<UpdateSubscriptionResponse> retrofitCall = this.apiSubscription.updateSubscription(subscriptionId, journeyId, originUserId, timeStamp, tppId, authorization, requestBody, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang, appName);
        Response<UpdateSubscriptionResponse> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (UpdateSubscriptionResponse)response.body();
        }
    }

    public List<SubscriptionView> getSubscriptionIdInfo(String subscriptionId, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String authorization = null;
        String timeStamp = this.getCurrentTimestamp().toString();
        Call<List<SubscriptionView>> retrofitCall = this.apiSubscription.getSubscriptionsForTPP(subscriptionId, journeyId, originUserId, timeStamp, tppId, (String)authorization, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang);
        Response<List<SubscriptionView>> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (List)response.body();
        }
    }

    public List<SubscriptionView> getAccountIdInfo(String accountId, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String subscriptionId = null;
        String authorization = null;
        Call<List<SubscriptionView>> retrofitCall = this.apiSubscription.getSubscriptionForAccount(accountId, journeyId, originUserId, timeStamp, tppId, (String)authorization, (String)subscriptionId, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang);
        Response<List<SubscriptionView>> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (List)response.body();
        }
    }

    public void revokeSubscription(String subscriptionId, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = null;
        Call<Void> retrofitCall = this.apiSubscription.revokeSubscription(subscriptionId, journeyId, originUserId, timeStamp, tppId, (String)authorization, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang);
        Response<Void> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        }
    }

    private Timestamp getCurrentTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }
}
