package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class PaymentView {
    @SerializedName("limit")
    private BigDecimal limit = null;
    @SerializedName("currency")
    private String currency = null;
    @SerializedName("amount")
    private BigDecimal amount = null;

    public PaymentView() {
    }

    public PaymentView limit(BigDecimal limit) {
        this.limit = limit;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public BigDecimal getLimit() {
        return this.limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public PaymentView currency(String currency) {
        this.currency = currency;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public PaymentView amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            PaymentView paymentView = (PaymentView)o;
            return Objects.equals(this.limit, paymentView.limit) && Objects.equals(this.currency, paymentView.currency) && Objects.equals(this.amount, paymentView.amount);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.limit, this.currency, this.amount});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PaymentView {\n");
        sb.append("    limit: ").append(this.toIndentedString(this.limit)).append("\n");
        sb.append("    currency: ").append(this.toIndentedString(this.currency)).append("\n");
        sb.append("    amount: ").append(this.toIndentedString(this.amount)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
