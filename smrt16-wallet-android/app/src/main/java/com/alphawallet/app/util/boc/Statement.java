package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Statement {
    @SerializedName("account")
    private Account account = null;
    @SerializedName("transaction")
    private List<Transaction> transaction = null;

    public Statement() {
    }

    public Statement account(Account account) {
        this.account = account;
        return this;
    }

//    @ApiModelProperty("")
    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Statement transaction(List<Transaction> transaction) {
        this.transaction = transaction;
        return this;
    }

    public Statement addTransactionItem(Transaction transactionItem) {
        if (this.transaction == null) {
            this.transaction = new ArrayList();
        }

        this.transaction.add(transactionItem);
        return this;
    }

//    @ApiModelProperty("")
    public List<Transaction> getTransaction() {
        return this.transaction;
    }

    public void setTransaction(List<Transaction> transaction) {
        this.transaction = transaction;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Statement statement = (Statement)o;
            return Objects.equals(this.account, statement.account) && Objects.equals(this.transaction, statement.transaction);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.account, this.transaction});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Statement {\n");
        sb.append("    account: ").append(this.toIndentedString(this.account)).append("\n");
        sb.append("    transaction: ").append(this.toIndentedString(this.transaction)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
