package com.alphawallet.app.ui.boc.adapter;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alphawallet.app.R;
import com.alphawallet.app.service.boc.BocAccountsService;
import com.alphawallet.app.service.boc.BocSubscriptionService;
import com.alphawallet.app.ui.boc.AccountDetailsActivity;
import com.alphawallet.app.ui.boc.PaymentActivity;
import com.alphawallet.app.util.boc.Account;
import com.alphawallet.app.util.boc.Statement;
import com.alphawallet.app.util.boc.Transaction;
import com.alphawallet.app.util.boc.Utilities;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class AccountsListAdapter extends RecyclerView.Adapter<AccountsListAdapter.ViewHolder> {
    private Context mContext;
    ArrayList<Account> accounts;
    private BocSubscriptionService mSubscriptionService = new BocSubscriptionService();
    private CompositeDisposable disposable = new CompositeDisposable();
    private BocAccountsService mAccountsService = new BocAccountsService();
    private final String LOGTAG = this.getClass().getName();
    private String subId;
    private Utilities utils = new Utilities();

    public AccountsListAdapter(Context context, ArrayList<Account> accounts) {
        mContext = context;
       this.accounts = accounts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);
        subId = PreferenceManager.getDefaultSharedPreferences(mContext).getString("SUB_ID", "NA");


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.accountName.setText(accounts.get(position).getAccountName());
        getAccountBalances(accounts.get(position).getAccountId(),subId,holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTransactions(accounts.get(position).getAccountId(),subId);

            }
        });
        holder.payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(utils.isNetworkAvailable(mContext)){
                    Intent intent = new Intent(mContext, PaymentActivity.class);
                    mContext.startActivity(intent);
                }
                else{
                    Toast.makeText(mContext,
                            "No network available, please connect!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            TextView accountName;
            TextView accountBalance;
            Button payButton;
        public ViewHolder(View itemView) {
            super(itemView);
            accountName = (TextView)itemView.findViewById(R.id.account_name);
            accountBalance = (TextView)itemView.findViewById(R.id.account_money);
            payButton = (Button) itemView.findViewById(R.id.pay_button);

        }
    }

    private void getTransactions(final String accId, final String subId) {

        disposable.add(
                mAccountsService
                        // Async call to BOC Java SDK library
                        .getAccountStatement(accId, subId).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<Statement>(){

                            @Override
                            public void onSuccess(Statement statement) {
                                Log.e(LOGTAG, " ------------- Statments success ---------------");
                                Transaction transaction = statement.getTransaction().get(0);
                                Log.e(LOGTAG, "Getting transactions");
                                Log.e(LOGTAG, transaction.getDescription());

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(LOGTAG, "onError: " + e.getMessage());

                            }
                        }));


    }


    private void getAccountDetails(final String accId, final String subId) {

        disposable.add(
                mAccountsService
                        // Async call to BOC Java SDK library
                        .getAccountDetails(accId, subId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Account>>() {
                            @Override
                            public void onSuccess(List<Account> response) {
                                // For debugging purposes
                                Log.e(LOGTAG, " ------------- Account success ---------------");
                                Log.e(LOGTAG, response.toString());

                                // Set loading spinner to invisible since we got success
//                                spinner.setVisibility(View.GONE);
                                // Convert API response to ArrayList<String> to set the next activity view
                               // accountDetailsRaw = response.toString();
                                // Start AccounDetailsActivity
                                Intent intent = new Intent(mContext, AccountDetailsActivity.class);
                                intent.putStringArrayListExtra("details", responseToArrayList(response));
                                mContext.startActivity(intent);
                            }

                            @Override
                            public void onError(Throwable e) {
//                                spinner.setVisibility(View.GONE);
                                Toast.makeText(mContext, "An error has occured. please check your connection", Toast.LENGTH_LONG).show();
                                Log.e(LOGTAG, "onError: " + e.getMessage());
                            }
                        }));
    }

    private void getAccountBalances(final String accId, final String subId,ViewHolder holder) {
        disposable.add(
                mAccountsService
                        // Async call to BOC Java SDK library
                        .getAccountDetails(accId, subId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Account>>() {
                            @Override
                            public void onSuccess(List<Account> response) {
                                // For debugging purposes
                                Log.e(LOGTAG, " ------------- Account success ---------------");
                                Log.e(LOGTAG, response.toString());
                                holder.accountBalance.setText(response.get(0).getBalances().get(0).getAmount().toString() + " EUR");
                            }

                            @Override
                            public void onError(Throwable e) {
//                                spinner.setVisibility(View.GONE);
                                Toast.makeText(mContext, "An error has occured. please check your connection", Toast.LENGTH_LONG).show();
                                Log.e(LOGTAG, "onError: " + e.getMessage());
                            }
                        }));
    }

    private ArrayList<String> responseToArrayList(List<Account> response){
        ArrayList<String> responseArray = new ArrayList<>();
        responseArray.add(response.get(0).getBankId());
        responseArray.add(response.get(0).getAccountId());
        responseArray.add(response.get(0).getAccountName());
        responseArray.add(response.get(0).getAccountType());
        responseArray.add(response.get(0).getIBAN());
        responseArray.add(response.get(0).getCurrency());
        responseArray.add(response.get(0).getInfoTimeStamp());
        responseArray.add(response.get(0).getInterestRate().toString());
        responseArray.add(response.get(0).getMaturityDate());
        responseArray.add(response.get(0).getLastPaymentDate());
        responseArray.add(response.get(0).getNextPaymentDate());
        responseArray.add(response.get(0).getRemainingInstallments().toString());
        responseArray.add(response.get(0).getBalances().get(0).getAmount().toString());
        responseArray.add(response.get(0).getBalances().get(1).getAmount().toString());
        return responseArray;
    }




}