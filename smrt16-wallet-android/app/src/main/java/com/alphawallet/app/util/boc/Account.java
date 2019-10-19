package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Account {
    @SerializedName("bankId")
    private String bankId = null;
    @SerializedName("accountId")
    private String accountId = null;
    @SerializedName("accountAlias")
    private String accountAlias = null;
    @SerializedName("accountType")
    private String accountType = null;
    @SerializedName("accountName")
    private String accountName = null;
    @SerializedName("IBAN")
    private String IBAN = null;
    @SerializedName("currency")
    private String currency = null;
    @SerializedName("infoTimeStamp")
    private String infoTimeStamp = null;
    @SerializedName("interestRate")
    private BigDecimal interestRate = null;
    @SerializedName("maturityDate")
    private String maturityDate = null;
    @SerializedName("lastPaymentDate")
    private String lastPaymentDate = null;
    @SerializedName("nextPaymentDate")
    private String nextPaymentDate = null;
    @SerializedName("remainingInstallments")
    private BigDecimal remainingInstallments = null;
    @SerializedName("balances")
    private List<Balance> balances = null;

    public Account() {
    }

    public Account bankId(String bankId) {
        this.bankId = bankId;
        return this;
    }

    //    @ApiModelProperty("")
    public String getBankId() {
        return this.bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public Account accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    //    @ApiModelProperty("")
    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public Account accountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
        return this;
    }

    //    @ApiModelProperty("")
    public String getAccountAlias() {
        return this.accountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        this.accountAlias = accountAlias;
    }

    public Account accountType(String accountType) {
        this.accountType = accountType;
        return this;
    }

    //    @ApiModelProperty("")
    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Account accountName(String accountName) {
        this.accountName = accountName;
        return this;
    }

    //    @ApiModelProperty("")
    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Account IBAN(String IBAN) {
        this.IBAN = IBAN;
        return this;
    }

    //    @ApiModelProperty("")
    public String getIBAN() {
        return this.IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }

    public Account currency(String currency) {
        this.currency = currency;
        return this;
    }

    //    @ApiModelProperty("")
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Account infoTimeStamp(String infoTimeStamp) {
        this.infoTimeStamp = infoTimeStamp;
        return this;
    }

    //    @ApiModelProperty("")
    public String getInfoTimeStamp() {
        return this.infoTimeStamp;
    }

    public void setInfoTimeStamp(String infoTimeStamp) {
        this.infoTimeStamp = infoTimeStamp;
    }

    public Account interestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    //    @ApiModelProperty("")
    public BigDecimal getInterestRate() {
        return this.interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public Account maturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
        return this;
    }

    //    @ApiModelProperty("")
    public String getMaturityDate() {
        return this.maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public Account lastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
        return this;
    }

    //    @ApiModelProperty("")
    public String getLastPaymentDate() {
        return this.lastPaymentDate;
    }

    public void setLastPaymentDate(String lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Account nextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
        return this;
    }

    //    @ApiModelProperty("")
    public String getNextPaymentDate() {
        return this.nextPaymentDate;
    }

    public void setNextPaymentDate(String nextPaymentDate) {
        this.nextPaymentDate = nextPaymentDate;
    }

    public Account remainingInstallments(BigDecimal remainingInstallments) {
        this.remainingInstallments = remainingInstallments;
        return this;
    }

    //    @ApiModelProperty("")
    public BigDecimal getRemainingInstallments() {
        return this.remainingInstallments;
    }

    public void setRemainingInstallments(BigDecimal remainingInstallments) {
        this.remainingInstallments = remainingInstallments;
    }

    public Account balances(List<Balance> balances) {
        this.balances = balances;
        return this;
    }

    public Account addBalancesItem(Balance balancesItem) {
        if (this.balances == null) {
            this.balances = new ArrayList();
        }

        this.balances.add(balancesItem);
        return this;
    }

    //    @ApiModelProperty("")
    public List<Balance> getBalances() {
        return this.balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Account account = (Account) o;
            return Objects.equals(this.bankId, account.bankId) && Objects.equals(this.accountId, account.accountId) && Objects.equals(this.accountAlias, account.accountAlias) && Objects.equals(this.accountType, account.accountType) && Objects.equals(this.accountName, account.accountName) && Objects.equals(this.IBAN, account.IBAN) && Objects.equals(this.currency, account.currency) && Objects.equals(this.infoTimeStamp, account.infoTimeStamp) && Objects.equals(this.interestRate, account.interestRate) && Objects.equals(this.maturityDate, account.maturityDate) && Objects.equals(this.lastPaymentDate, account.lastPaymentDate) && Objects.equals(this.nextPaymentDate, account.nextPaymentDate) && Objects.equals(this.remainingInstallments, account.remainingInstallments) && Objects.equals(this.balances, account.balances);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.bankId, this.accountId, this.accountAlias, this.accountType, this.accountName, this.IBAN, this.currency, this.infoTimeStamp, this.interestRate, this.maturityDate, this.lastPaymentDate, this.nextPaymentDate, this.remainingInstallments, this.balances});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Account {\n");
        sb.append("    bankId: ").append(this.toIndentedString(this.bankId)).append("\n");
        sb.append("    accountId: ").append(this.toIndentedString(this.accountId)).append("\n");
        sb.append("    accountAlias: ").append(this.toIndentedString(this.accountAlias)).append("\n");
        sb.append("    accountType: ").append(this.toIndentedString(this.accountType)).append("\n");
        sb.append("    accountName: ").append(this.toIndentedString(this.accountName)).append("\n");
        sb.append("    IBAN: ").append(this.toIndentedString(this.IBAN)).append("\n");
        sb.append("    currency: ").append(this.toIndentedString(this.currency)).append("\n");
        sb.append("    infoTimeStamp: ").append(this.toIndentedString(this.infoTimeStamp)).append("\n");
        sb.append("    interestRate: ").append(this.toIndentedString(this.interestRate)).append("\n");
        sb.append("    maturityDate: ").append(this.toIndentedString(this.maturityDate)).append("\n");
        sb.append("    lastPaymentDate: ").append(this.toIndentedString(this.lastPaymentDate)).append("\n");
        sb.append("    nextPaymentDate: ").append(this.toIndentedString(this.nextPaymentDate)).append("\n");
        sb.append("    remainingInstallments: ").append(this.toIndentedString(this.remainingInstallments)).append("\n");
        sb.append("    balances: ").append(this.toIndentedString(this.balances)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
