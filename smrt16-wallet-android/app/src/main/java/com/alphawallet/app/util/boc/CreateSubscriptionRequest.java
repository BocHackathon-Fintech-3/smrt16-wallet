package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CreateSubscriptionRequest {
    @SerializedName("selectedAccounts")
    private List<SelectedAccount> selectedAccounts = null;
    @SerializedName("accounts")
    private AccountView accounts = null;
    @SerializedName("payments")
    private PaymentView payments = null;

    public CreateSubscriptionRequest() {
    }

    public CreateSubscriptionRequest selectedAccounts(List<SelectedAccount> selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
        return this;
    }

    public CreateSubscriptionRequest addSelectedAccountsItem(SelectedAccount selectedAccountsItem) {
        if (this.selectedAccounts == null) {
            this.selectedAccounts = new ArrayList();
        }

        this.selectedAccounts.add(selectedAccountsItem);
        return this;
    }

//    @ApiModelProperty("")
    public List<SelectedAccount> getSelectedAccounts() {
        return this.selectedAccounts;
    }

    public void setSelectedAccounts(List<SelectedAccount> selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
    }

    public CreateSubscriptionRequest accounts(AccountView accounts) {
        this.accounts = accounts;
        return this;
    }

//    @ApiModelProperty("")
    public AccountView getAccounts() {
        return this.accounts;
    }

    public void setAccounts(AccountView accounts) {
        this.accounts = accounts;
    }

    public CreateSubscriptionRequest payments(PaymentView payments) {
        this.payments = payments;
        return this;
    }

//    @ApiModelProperty("")
    public PaymentView getPayments() {
        return this.payments;
    }

    public void setPayments(PaymentView payments) {
        this.payments = payments;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            CreateSubscriptionRequest createSubscriptionRequest = (CreateSubscriptionRequest)o;
            return Objects.equals(this.selectedAccounts, createSubscriptionRequest.selectedAccounts) && Objects.equals(this.accounts, createSubscriptionRequest.accounts) && Objects.equals(this.payments, createSubscriptionRequest.payments);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.selectedAccounts, this.accounts, this.payments});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateSubscriptionRequest {\n");
        sb.append("    selectedAccounts: ").append(this.toIndentedString(this.selectedAccounts)).append("\n");
        sb.append("    accounts: ").append(this.toIndentedString(this.accounts)).append("\n");
        sb.append("    payments: ").append(this.toIndentedString(this.payments)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
