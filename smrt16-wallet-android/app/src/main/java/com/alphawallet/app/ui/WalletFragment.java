package com.alphawallet.app.ui;

import android.app.ActionBar;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alphawallet.app.entity.BackupTokenCallback;
import com.alphawallet.app.entity.ContractResult;
import com.alphawallet.app.entity.ErrorEnvelope;
import com.alphawallet.app.entity.NetworkInfo;
import com.alphawallet.app.entity.Token;
import com.alphawallet.app.entity.TokenInterface;
import com.alphawallet.app.entity.TokensReceiver;
import com.alphawallet.app.entity.Wallet;
import com.alphawallet.app.repository.EthereumNetworkRepository;
import com.alphawallet.app.service.boc.BocAccountsService;
import com.alphawallet.app.service.boc.BocAuthorizationService;
import com.alphawallet.app.service.boc.BocSubscriptionService;
import com.alphawallet.app.ui.boc.AccountActivity;
import com.alphawallet.app.ui.boc.adapter.AccountsListAdapter;
import com.alphawallet.app.ui.widget.OnTokenClickListener;
import com.alphawallet.app.ui.widget.adapter.TokensAdapter;
import com.alphawallet.app.ui.widget.entity.WarningData;
import com.alphawallet.app.ui.widget.holder.WarningHolder;
import com.alphawallet.app.util.TabUtils;

import dagger.android.support.AndroidSupportInjection;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import com.alphawallet.app.C;
import com.alphawallet.app.R;
import com.alphawallet.app.interact.GenericWalletInteract;
import com.alphawallet.app.util.boc.AccessTokenResponse;
import com.alphawallet.app.util.boc.Account;
import com.alphawallet.app.util.boc.BocUtilities;
import com.alphawallet.app.util.boc.SubscriptionView;
import com.alphawallet.app.util.boc.UpdateSubscriptionResponse;
import com.alphawallet.app.util.boc.Utilities;
import com.alphawallet.app.viewmodel.WalletViewModel;
import com.alphawallet.app.viewmodel.WalletViewModelFactory;
import com.alphawallet.app.widget.ProgressView;
import com.alphawallet.app.widget.SystemView;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

import static com.alphawallet.app.C.ErrorCode.EMPTY_COLLECTION;
import static com.alphawallet.app.C.Key.WALLET;

/**
 * Created by justindeguzman on 2/28/18.
 */

public class WalletFragment extends Fragment implements OnTokenClickListener, View.OnClickListener, TokenInterface, Runnable, BackupTokenCallback
{
    private static final String TAG = "WFRAG";

    @Inject
    WalletViewModelFactory walletViewModelFactory;
    private WalletViewModel viewModel;

    private TokensReceiver tokenReceiver;

    private SystemView systemView;
    private ProgressView progressView;
    TextView total_amount;
    private AccountsListAdapter adapter;
    private View selectedToken;
    private Handler handler;
    private boolean isVisible;
    private final String LOGTAG = this.getClass().getName();
    private CompositeDisposable disposable = new CompositeDisposable();
    private BocAccountsService mAccountsService = new BocAccountsService();
    String subId;
    //SwipeRefreshLayout refreshLayout;
    RecyclerView list;
    private String authCode;
    private boolean isSubscriptionCreated = false;
    private boolean isSubscriptionUpdated = false;
    private Utilities utils = new Utilities();
    private BocAuthorizationService mAuthorizationSerivce = new BocAuthorizationService();
    private BocSubscriptionService mSubscriptionService = new BocSubscriptionService();
    private String token2;
    private long balance = 0;
    TabLayout tabLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        total_amount = view.findViewById(R.id.total_amount);
       // refreshLayout = view.findViewById(R.id.refresh_layout);
        systemView = view.findViewById(R.id.system_view);
    //    progressView = view.findViewById(R.id.progress_view);
//        progressView.setWhiteCircle();
       // progressView.hide();

        subId = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("SUB_ID", "NA");

        list = view.findViewById(R.id.list);

        systemView.attachRecyclerView(list);
       // systemView.attachSwipeRefreshLayout(refreshLayout);

        viewModel = ViewModelProviders.of(this, walletViewModelFactory)
                .get(WalletViewModel.class);
        //viewModel.progress().observe(this, systemView::showProgress);
        viewModel.error().observe(this, this::onError);
        viewModel.tokens().observe(this, this::onTokens);
        viewModel.total().observe(this, this::onTotal);
        //viewModel.queueProgress().observe(this, progressView::updateProgress);
        viewModel.currentWalletBalance().observe(this, this::onBalanceChanged);
        viewModel.refreshTokens().observe(this, this::refreshTokens);
        viewModel.tokenUpdate().observe(this, this::onToken);
        viewModel.tokensReady().observe(this, this::tokensReady);
        viewModel.fetchKnownContracts().observe(this, this::fetchKnownContracts);
        viewModel.backupEvent().observe(this, this::backupEvent);
        authCode = PreferenceManager.getDefaultSharedPreferences(getContext()).getString("authCode", "");

        //if(authCode.equals("")) {
//            if (getActivity().getIntent().getData() != null) {
//                authCode = getActivity().getIntent().getData().getQueryParameter("code");
                if (!authCode.equals("")) {
                    if (utils.isNetworkAvailable(getContext())) {
                        //spinner.setVisibility(View.VISIBLE);
                        patchSubscription(authCode, subId);
                    } else {
                        Toast.makeText(getContext(),
                                "No network available, please connect!",
                                Toast.LENGTH_LONG).show();
                    }
                }
//            }
//        }else {
//            if(utils.isNetworkAvailable(getContext())){
//                //spinner.setVisibility(View.VISIBLE);
//                patchSubscription(authCode, subId);
//               // getAccounts(subId);
//            }
//            else{
//                Toast.makeText(getContext(),
//                        "No network available, please connect!",
//                        Toast.LENGTH_LONG).show();
//            }
//        }
        tokenReceiver = new TokensReceiver(getActivity(), this);

        initTabLayout(view);

        isVisible = true;

        viewModel.clearProcess();


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        getAccounts(subId);
    }

    public void setupAdapter(ArrayList<Account> accounts){
//        adapter = new TokensAdapter(getActivity(),this, viewModel.getAssetDefinitionService());
//        adapter.setHasStableIds(true);
//        list.setLayoutManager(new LinearLayoutManager(getContext()));
//        list.setAdapter(adapter);
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeCallback(adapter));
//        itemTouchHelper.attachToRecyclerView(list);
//        refreshLayout.setOnRefreshListener(this::refreshList);
        adapter = new AccountsListAdapter(getContext(),accounts);
        adapter.setHasStableIds(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(adapter);

    }
    private void refreshList()
    {
       // adapter.setClear();
        viewModel.reloadTokens();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = isVisibleToUser;
        if (isResumed()) { // fragment created
            viewModel.setVisibility(isVisible);
            if (isVisible) viewModel.prepare();
        }
    }

    @Override
    public void onPause()
    {
        super.onPause();
        viewModel.setVisibility(false);
    }

    private void onToken(Token token)
    {
       // adapter.updateToken(token, false);
    }

    private void initTabLayout(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.all));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.currency));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.collectibles));

//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch(tab.getPosition()) {
//                    case 0:
//                        adapter.setFilterType(TokensAdapter.FILTER_ALL);
//                        viewModel.fetchTokens();
//                        break;
//                    case 1:
//                        adapter.setFilterType(TokensAdapter.FILTER_CURRENCY);
//                        viewModel.fetchTokens();
//                        break;
//                    case 2:
//                        adapter.setFilterType(TokensAdapter.FILTER_COLLECTIBLES);
//                        viewModel.fetchTokens();
//                        break;
//                    default:
//                        break;
//                }
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

        TabUtils.changeTabsFont(getContext(), tabLayout);
        TabUtils.reflex(tabLayout);
    }

    private void onTotal(BigDecimal totalInCurrency) {
        //adapter.setTotal(totalInCurrency);
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_add: {
//                viewModel.showAddToken(getActivity());
//            }
//            break;
//            case android.R.id.home: {
//                adapter.clear();
//                viewModel.showTransactions(getContext());
//            }
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onTokenClick(View view, Token token, List<BigInteger> ids, boolean selected) {
        if (selectedToken == null)
        {
            selectedToken = view;
            token = viewModel.getTokenFromService(token);
            token.clickReact(viewModel, getActivity());
            handler.postDelayed(this, 700);
        }
    }

    @Override
    public void onLongTokenClick(View view, Token token, List<BigInteger> tokenId)
    {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (handler == null) handler = new Handler();
        selectedToken = null;
        viewModel.setVisibility(isVisible);
        viewModel.prepare();
    }

    private void onTokens(Token[] tokens)
    {
        if (tokens != null)
        {
           // adapter.setTokens(tokens);
        }
    }

    private void backupEvent(GenericWalletInteract.BackupLevel backupLevel) {
//        if (adapter.hasBackupWarning()) return;
//
//        WarningData wData;
//        switch (backupLevel) {
//            case BACKUP_NOT_REQUIRED:
//                //if (getActivity() != null) ((HomeActivity) getActivity()).removeSettingsBadgeKey(C.KEY_NEEDS_BACKUP);
//                break;
//            case WALLET_HAS_LOW_VALUE:
//                wData = new WarningData(this);
//                wData.title = getString(R.string.time_to_backup_wallet);
//                wData.detail = getString(R.string.recommend_monthly_backup);
//                wData.buttonText = getString(R.string.back_up_wallet_action, viewModel.getWalletAddr().substring(0, 5));
//                wData.colour = ContextCompat.getColor(getContext(), R.color.slate_grey);
//                wData.buttonColour = ContextCompat.getColor(getContext(), R.color.backup_grey);
//                wData.wallet = viewModel.getWallet();
//                adapter.addWarning(wData);
//                break;
//            case WALLET_HAS_HIGH_VALUE:
//                wData = new WarningData(this);
//                wData.title = getString(R.string.wallet_not_backed_up);
//                wData.detail = getString(R.string.not_backed_up_detail);
//                wData.buttonText = getString(R.string.back_up_wallet_action, viewModel.getWalletAddr().substring(0, 5));
//                wData.colour = ContextCompat.getColor(getContext(), R.color.warning_red);
//                wData.buttonColour = ContextCompat.getColor(getContext(), R.color.warning_dark_red);
//                wData.wallet = viewModel.getWallet();
//                adapter.addWarning(wData);
//                break;
//        }
    }

    private void onError(ErrorEnvelope errorEnvelope) {
//        if (errorEnvelope.code == EMPTY_COLLECTION) {
//            systemView.showEmpty(getString(R.string.no_tokens));
//        } else {
//            systemView.showError(getString(R.string.error_fail_load_tokens), this);
//        }
    }

    @Override
    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.try_again: {
//                viewModel.fetchTokens();
//            }
//            break;
//        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        getActivity().unregisterReceiver(tokenReceiver);
        viewModel.clearProcess();
    }

    private void onBalanceChanged(Map<String, String> balance) {
//        ActionBar actionBar = getSupportActionBar();
//        NetworkInfo networkInfo = viewModel.defaultNetwork().getValue();
//        Wallet wallet = viewModel.defaultWallet().getValue();
//        if (actionBar == null || networkInfo == null || wallet == null) {
//            return;
//        }
//        if (TextUtils.isEmpty(balance.get(C.USD_SYMBOL))) {
//            actionBar.setTitle(balance.get(networkInfo.symbol) + " " + networkInfo.symbol);
//            actionBar.setSubtitle("");
//        } else {
//            actionBar.setTitle("$" + balance.get(C.USD_SYMBOL));
//            actionBar.setSubtitle(balance.get(networkInfo.symbol) + " " + networkInfo.symbol);
//        }
    }

    /**
     * This is triggered by transaction view after we have found new tokens by scanning the transaction history
     * @param aBoolean - dummy param
     */
    private void refreshTokens(Boolean aBoolean)
    {
        //adapter.clear();
        viewModel.resetAndFetchTokens();
    }

    private void tokensReady(Boolean dummy)
    {
        if (getActivity() != null) ((HomeActivity)getActivity()).TokensReady();
    }

    private void fetchKnownContracts(Boolean notUsed)
    {
        //fetch list of contracts for this network from the XML contract directory
        List<ContractResult> knownContracts = EthereumNetworkRepository.getAllKnownContracts(getContext(), viewModel.getChainFilters());
        viewModel.checkKnownContracts(knownContracts);
    }

    @Override
    public void resetTokens()
    {
        //first abort the current operation
        viewModel.clearProcess();
       // adapter.clear();
        viewModel.prepare();
    }

    @Override
    public void addedToken()
    {

    }

    @Override
    public void changedLocale()
    {

    }

    public void walletOutOfFocus()
    {
        if (viewModel != null) viewModel.clearProcess();
    }

    public void walletInFocus()
    {
        if (viewModel != null) viewModel.reloadTokens();
    }

    @Override
    public void run()
    {
        if (selectedToken != null && selectedToken.findViewById(R.id.token_layout) != null)
        {
            selectedToken.findViewById(R.id.token_layout).setBackgroundResource(R.drawable.background_marketplace_event);
        }
        selectedToken = null;
    }

    @Override
    public void BackupClick(Wallet wallet)
    {
        Intent intent = new Intent(getContext(), BackupKeyActivity.class);
        intent.putExtra(WALLET, wallet);

        switch (viewModel.getWalletType())
        {
            case HDKEY:
                intent.putExtra("TYPE", BackupKeyActivity.BackupOperationType.BACKUP_HD_KEY);
                break;
            case KEYSTORE:
                intent.putExtra("TYPE", BackupKeyActivity.BackupOperationType.BACKUP_KEYSTORE_KEY);
                break;
        }

        intent.setFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        getActivity().startActivityForResult(intent, C.REQUEST_BACKUP_WALLET);
    }

    @Override
    public void remindMeLater(Wallet wallet)
    {
        if (viewModel != null) viewModel.setKeyWarningDismissTime(wallet.address).isDisposed();
 //       if (adapter != null) adapter.removeBackupWarning();
    }

    public void storeWalletBackupTime(String backedUpKey)
    {
        if (viewModel != null) viewModel.setKeyBackupTime(backedUpKey).isDisposed();
    //    if (adapter != null) adapter.removeBackupWarning();
    }

    public class SwipeCallback extends ItemTouchHelper.SimpleCallback {
        private TokensAdapter mAdapter;

        public SwipeCallback(TokensAdapter adapter) {
            super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
            mAdapter = adapter;
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            remindMeLater(viewModel.getWallet());
        }

//        @Override
//        public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y)
//        {
//            super.onMoved(recyclerView, viewHolder, fromPos, target, toPos, x, y);
//        }

        @Override
        public int getSwipeDirs(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            if (viewHolder instanceof WarningHolder) {
                return super.getSwipeDirs(recyclerView, viewHolder);
            } else {
                return 0;
            }
        }
    }

    private void patchSubscription(String code, final String subId){
        disposable.add(
                mAuthorizationSerivce
                        // Async call to BOC Java SDK library
                        .getAccessToken2(code)
                        // Async call to BOC Java SDK library. Get the response from getAccessToken2 and pass it down the chain
                        .flatMap(new Function<AccessTokenResponse, SingleSource<List<SubscriptionView>>>() {
                            @Override
                            public SingleSource<List<SubscriptionView>> apply(@io.reactivex.annotations.NonNull AccessTokenResponse accessTokenResponse) throws Exception {
                                token2 = accessTokenResponse.getAccessToken();
                                return mSubscriptionService.getSubscriptionIdInfo(subId);
                            }
                        })
                        // Async call to BOC Java SDK library. Get the response from getSubscriptionID info, as well as the accessToken, and patch the subscription
                        .flatMap(new Function<List<SubscriptionView>, SingleSource<UpdateSubscriptionResponse>>() {
                            @Override
                            public SingleSource<UpdateSubscriptionResponse> apply(List<SubscriptionView> subscriptionViewList) throws Exception {
                                return mSubscriptionService.patchSubscriptionIdInfo(subscriptionViewList,subId,token2);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<UpdateSubscriptionResponse>() {
                            @Override
                            public void onSuccess(UpdateSubscriptionResponse response) {
                                Log.e(LOGTAG, " ------------- Patch Subscription success ---------------");
                                Log.e(LOGTAG,  response.toString());

                                // Change flags and disable/enable buttons to make the user flow more straightforward
                                isSubscriptionUpdated = true;
                                PreferenceManager.getDefaultSharedPreferences(getContext()).edit().putBoolean("Subscription", isSubscriptionUpdated).apply();

                                getAccounts(subId);
                                // Storing user API Key in preferences
                                Toast.makeText(getContext(),
                                        "Patch Subscription successful!! ",
                                        Toast.LENGTH_LONG).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Toast.makeText(getContext(), "An error has occured. please check your connection", Toast.LENGTH_LONG).show();
                                Log.e(LOGTAG, "onError: " + e.getMessage());
                            }
                        }));
    }
    private void getAccounts(final String subId) {
        disposable.add(
                mAccountsService
                        // Async call to BOC Java SDK library
                        .getAccounts(subId)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Account>>() {
                            @Override
                            public void onSuccess(List<Account> response) {
                                Log.e(LOGTAG, " ------------- Payment success ---------------");
                                Log.e(LOGTAG, response.toString());
                                // spinner.setVisibility(View.GONE);
                                BocUtilities.getInstance().accountListNames.clear();
                                BocUtilities.getInstance().accountList.clear();
                                BocUtilities.getInstance().accountListID.clear();
                                balance = 0;
                                // Convert the API response to ArrayList<String>
                                for (Account a: response
                                ) {
                                    BocUtilities.getInstance().accountList.add(a);
                                    BocUtilities.getInstance().accountListID.add(a.getAccountId());
                                    BocUtilities.getInstance().accountListNames.add(a.getAccountName());
                                    getAccountBalances(a.getAccountId(),subId);
                                }

                                // Start the Accounts Activity
                                setupAdapter(BocUtilities.getInstance().accountList);
//                                Intent intent = new Intent(getContext(), AccountActivity.class);
//                                intent.putStringArrayListExtra("list", accountIdList);
//                                startActivity(intent);
                            }

                            @Override
                            public void onError(Throwable e) {
                                // spinner.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "An error has occured. please check your connection", Toast.LENGTH_LONG).show();
                                Log.e(LOGTAG, "onError: " + e.getMessage());
                            }
                        }));
    }

    private void getAccountBalances(final String accId, final String subId) {
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
                                balance = balance + response.get(0).getBalances().get(0).getAmount().longValueExact();
                                total_amount.setText("TOTAL AMOUNT: "+balance+ " EUR");
                            }

                            @Override
                            public void onError(Throwable e) {
//                                spinner.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "An error has occured. please check your connection", Toast.LENGTH_LONG).show();
                                Log.e(LOGTAG, "onError: " + e.getMessage());
                            }
                        }));
    }


}
