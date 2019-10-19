package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UpdateSubscriptionResponse {
    @SerializedName("subscriptionId")
    private String subscriptionId = null;
    @SerializedName("status")
    private String status = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("selectedAccounts")
    private List<SelectedAccount> selectedAccounts = null;
    @SerializedName("accounts")
    private AccountView accounts = null;
    @SerializedName("payments")
    private PaymentView payments = null;
    @SerializedName("duration")
    private Duration duration = null;

    public UpdateSubscriptionResponse() {
    }

    public UpdateSubscriptionResponse subscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        return this;
    }

  //  @ApiModelProperty("")
    public String getSubscriptionId() {
        return this.subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public UpdateSubscriptionResponse status(String status) {
        this.status = status;
        return this;
    }

//    @ApiModelProperty(
//            example = "ACTV",
//            value = ""
//    )
    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdateSubscriptionResponse description(String description) {
        this.description = description;
        return this;
    }

//    @ApiModelProperty(
//            example = "SUBSCRIPTION",
//            value = ""
//    )
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UpdateSubscriptionResponse selectedAccounts(List<SelectedAccount> selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
        return this;
    }

    public UpdateSubscriptionResponse addSelectedAccountsItem(SelectedAccount selectedAccountsItem) {
        if (this.selectedAccounts == null) {
            this.selectedAccounts = new ArrayList();
        }

        this.selectedAccounts.add(selectedAccountsItem);
        return this;
    }

   // @ApiModelProperty("")
    public List<SelectedAccount> getSelectedAccounts() {
        return this.selectedAccounts;
    }

    public void setSelectedAccounts(List<SelectedAccount> selectedAccounts) {
        this.selectedAccounts = selectedAccounts;
    }

    public UpdateSubscriptionResponse accounts(AccountView accounts) {
        this.accounts = accounts;
        return this;
    }

   // @ApiModelProperty("")
    public AccountView getAccounts() {
        return this.accounts;
    }

    public void setAccounts(AccountView accounts) {
        this.accounts = accounts;
    }

    public UpdateSubscriptionResponse payments(PaymentView payments) {
        this.payments = payments;
        return this;
    }

   // @ApiModelProperty("")
    public PaymentView getPayments() {
        return this.payments;
    }

    public void setPayments(PaymentView payments) {
        this.payments = payments;
    }

    public UpdateSubscriptionResponse duration(Duration duration) {
        this.duration = duration;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public Duration getDuration() {
        return this.duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            UpdateSubscriptionResponse updateSubscriptionResponse = (UpdateSubscriptionResponse)o;
            return Objects.equals(this.subscriptionId, updateSubscriptionResponse.subscriptionId) && Objects.equals(this.status, updateSubscriptionResponse.status) && Objects.equals(this.description, updateSubscriptionResponse.description) && Objects.equals(this.selectedAccounts, updateSubscriptionResponse.selectedAccounts) && Objects.equals(this.accounts, updateSubscriptionResponse.accounts) && Objects.equals(this.payments, updateSubscriptionResponse.payments) && Objects.equals(this.duration, updateSubscriptionResponse.duration);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.subscriptionId, this.status, this.description, this.selectedAccounts, this.accounts, this.payments, this.duration});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class UpdateSubscriptionResponse {\n");
        sb.append("    subscriptionId: ").append(this.toIndentedString(this.subscriptionId)).append("\n");
        sb.append("    status: ").append(this.toIndentedString(this.status)).append("\n");
        sb.append("    description: ").append(this.toIndentedString(this.description)).append("\n");
        sb.append("    selectedAccounts: ").append(this.toIndentedString(this.selectedAccounts)).append("\n");
        sb.append("    accounts: ").append(this.toIndentedString(this.accounts)).append("\n");
        sb.append("    payments: ").append(this.toIndentedString(this.payments)).append("\n");
        sb.append("    duration: ").append(this.toIndentedString(this.duration)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
