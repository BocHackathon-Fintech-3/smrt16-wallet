package com.alphawallet.app.service.boc;

import com.alphawallet.app.util.boc.Account;
import com.alphawallet.app.util.boc.AccountsApi;
import com.alphawallet.app.util.boc.ApiClient;
import com.alphawallet.app.util.boc.ApiConfiguration;
import com.alphawallet.app.util.boc.Balance;
import com.alphawallet.app.util.boc.Statement;

import java.io.IOException;
import java.math.BigDecimal;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class AccountsService implements ApiConfiguration {
    private AccountsApi apiAccounts;

    public AccountsService() {
        String[] auth = new String[]{"Application_oauth", "Client_id", "Client_secret"};
        this.apiAccounts = (AccountsApi)(new ApiClient(auth)).createService(AccountsApi.class);
    }

    public List<Account> getAccounts(String subscriptionId, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang, String customerId, Boolean onlineAccessFlag) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = null;
        Call<List<Account>> retrofitCall = this.apiAccounts.getAccounts((String)authorization, originUserId, journeyId, timeStamp, tppId, subscriptionId, customerId, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang, onlineAccessFlag);
        Response<List<Account>> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (List)response.body();
        }
    }

    public List<Account> getAccountDetails(String accountId, String subscriptionId, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang, Boolean onlineAccessFlag) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = null;
        Call<List<Account>> retrofitCall = this.apiAccounts.getAccountDetails(accountId, journeyId, originUserId, timeStamp, tppId, subscriptionId, (String)authorization, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, onlineAccessFlag, lang);
        Response<List<Account>> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (List)response.body();
        }
    }

    public List<Balance> getAvailableBalance(String accountId, String subscriptionId, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = null;
        Call<List<Account>> retrofitCall = this.apiAccounts.getAvailableBalance(accountId, journeyId, originUserId, timeStamp, tppId, subscriptionId, (String)authorization, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang);
        Response<List<Account>> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return ((Account)((List)response.body()).get(0)).getBalances();
        }
    }

    public Statement getAccountStatement(String accountId, String subscriptionId, String startDate, String endDate, BigDecimal maxCount, String journeyId, String originUserId, String tppId, String originSourceId, String originChannelId, String originDeptId, String originEmployeeId, String originTerminalId, String correlationId, String lang) throws IOException {
        String timeStamp = this.getCurrentTimestamp().toString();
        String authorization = null;
        Call<Statement> retrofitCall = this.apiAccounts.getAccountStatement(accountId, journeyId, originUserId, timeStamp, tppId, subscriptionId, (String)authorization, originSourceId, originChannelId, originDeptId, originEmployeeId, originTerminalId, correlationId, lang, startDate, endDate, maxCount);
        Response<Statement> response = retrofitCall.execute();
        if (!response.isSuccessful()) {
            throw new IOException(response.errorBody() != null ? response.errorBody().string() : "Unknown error");
        } else {
            return (Statement)response.body();
        }
    }

    private Timestamp getCurrentTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return new Timestamp(time);
    }
}
