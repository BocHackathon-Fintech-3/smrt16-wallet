package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Transaction {
    @SerializedName("id")
    private String id = null;
    @SerializedName("dcInd")
    private String dcInd = null;
    @SerializedName("transactionAmount")
    private Amount transactionAmount = null;
    @SerializedName("description")
    private String description = null;
    @SerializedName("postingDate")
    private String postingDate = null;
    @SerializedName("valueDate")
    private String valueDate = null;
    @SerializedName("transactionType")
    private String transactionType = null;
    @SerializedName("merchant")
    private Merchant merchant = null;
    @SerializedName("terminalId")
    private String terminalId = null;

    public Transaction() {
    }

    public Transaction id(String id) {
        this.id = id;
        return this;
    }

//    @ApiModelProperty("")
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Transaction dcInd(String dcInd) {
        this.dcInd = dcInd;
        return this;
    }

//    @ApiModelProperty("")
    public String getDcInd() {
        return this.dcInd;
    }

    public void setDcInd(String dcInd) {
        this.dcInd = dcInd;
    }

    public Transaction transactionAmount(Amount transactionAmount) {
        this.transactionAmount = transactionAmount;
        return this;
    }

//    @ApiModelProperty("")
    public Amount getTransactionAmount() {
        return this.transactionAmount;
    }

    public void setTransactionAmount(Amount transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public Transaction description(String description) {
        this.description = description;
        return this;
    }

//    @ApiModelProperty("")
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Transaction postingDate(String postingDate) {
        this.postingDate = postingDate;
        return this;
    }

//    @ApiModelProperty("")
    public String getPostingDate() {
        return this.postingDate;
    }

    public void setPostingDate(String postingDate) {
        this.postingDate = postingDate;
    }

    public Transaction valueDate(String valueDate) {
        this.valueDate = valueDate;
        return this;
    }

//    @ApiModelProperty("")
    public String getValueDate() {
        return this.valueDate;
    }

    public void setValueDate(String valueDate) {
        this.valueDate = valueDate;
    }

    public Transaction transactionType(String transactionType) {
        this.transactionType = transactionType;
        return this;
    }

//    @ApiModelProperty("")
    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public Transaction merchant(Merchant merchant) {
        this.merchant = merchant;
        return this;
    }

//    @ApiModelProperty("")
    public Merchant getMerchant() {
        return this.merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public Transaction terminalId(String terminalId) {
        this.terminalId = terminalId;
        return this;
    }

//    @ApiModelProperty("")
    public String getTerminalId() {
        return this.terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Transaction transaction = (Transaction)o;
            return Objects.equals(this.id, transaction.id) && Objects.equals(this.dcInd, transaction.dcInd) && Objects.equals(this.transactionAmount, transaction.transactionAmount) && Objects.equals(this.description, transaction.description) && Objects.equals(this.postingDate, transaction.postingDate) && Objects.equals(this.valueDate, transaction.valueDate) && Objects.equals(this.transactionType, transaction.transactionType) && Objects.equals(this.merchant, transaction.merchant) && Objects.equals(this.terminalId, transaction.terminalId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.id, this.dcInd, this.transactionAmount, this.description, this.postingDate, this.valueDate, this.transactionType, this.merchant, this.terminalId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Transaction {\n");
        sb.append("    id: ").append(this.toIndentedString(this.id)).append("\n");
        sb.append("    dcInd: ").append(this.toIndentedString(this.dcInd)).append("\n");
        sb.append("    transactionAmount: ").append(this.toIndentedString(this.transactionAmount)).append("\n");
        sb.append("    description: ").append(this.toIndentedString(this.description)).append("\n");
        sb.append("    postingDate: ").append(this.toIndentedString(this.postingDate)).append("\n");
        sb.append("    valueDate: ").append(this.toIndentedString(this.valueDate)).append("\n");
        sb.append("    transactionType: ").append(this.toIndentedString(this.transactionType)).append("\n");
        sb.append("    merchant: ").append(this.toIndentedString(this.merchant)).append("\n");
        sb.append("    terminalId: ").append(this.toIndentedString(this.terminalId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}

