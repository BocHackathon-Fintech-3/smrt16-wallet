package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class AccountView {
    @SerializedName("transactionHistory")
    private Boolean transactionHistory = null;
    @SerializedName("balance")
    private Boolean balance = null;
    @SerializedName("details")
    private Boolean details = null;
    @SerializedName("checkFundsAvailability")
    private Boolean checkFundsAvailability = null;

    public AccountView() {
    }

    public AccountView transactionHistory(Boolean transactionHistory) {
        this.transactionHistory = transactionHistory;
        return this;
    }
//
//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public Boolean isTransactionHistory() {
        return this.transactionHistory;
    }

    public void setTransactionHistory(Boolean transactionHistory) {
        this.transactionHistory = transactionHistory;
    }

    public AccountView balance(Boolean balance) {
        this.balance = balance;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public Boolean isBalance() {
        return this.balance;
    }

    public void setBalance(Boolean balance) {
        this.balance = balance;
    }

    public AccountView details(Boolean details) {
        this.details = details;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public Boolean isDetails() {
        return this.details;
    }

    public void setDetails(Boolean details) {
        this.details = details;
    }

    public AccountView checkFundsAvailability(Boolean checkFundsAvailability) {
        this.checkFundsAvailability = checkFundsAvailability;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public Boolean isCheckFundsAvailability() {
        return this.checkFundsAvailability;
    }

    public void setCheckFundsAvailability(Boolean checkFundsAvailability) {
        this.checkFundsAvailability = checkFundsAvailability;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AccountView accountView = (AccountView)o;
            return Objects.equals(this.transactionHistory, accountView.transactionHistory) && Objects.equals(this.balance, accountView.balance) && Objects.equals(this.details, accountView.details) && Objects.equals(this.checkFundsAvailability, accountView.checkFundsAvailability);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.transactionHistory, this.balance, this.details, this.checkFundsAvailability});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AccountView {\n");
        sb.append("    transactionHistory: ").append(this.toIndentedString(this.transactionHistory)).append("\n");
        sb.append("    balance: ").append(this.toIndentedString(this.balance)).append("\n");
        sb.append("    details: ").append(this.toIndentedString(this.details)).append("\n");
        sb.append("    checkFundsAvailability: ").append(this.toIndentedString(this.checkFundsAvailability)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
