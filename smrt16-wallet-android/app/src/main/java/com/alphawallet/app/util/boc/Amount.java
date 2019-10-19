package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class Amount {
    @SerializedName("amount")
    private BigDecimal amount = null;
    @SerializedName("currency")
    private String currency = null;
    @SerializedName("currencyRate")
    private String currencyRate = null;

    public Amount() {
    }

    public Amount amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = "Amount is in EUR"
//    )
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Amount currency(String currency) {
        this.currency = currency;
        return this;
    }

//    @ApiModelProperty("3 Character ISO code")
    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Amount currencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
        return this;
    }

//    @ApiModelProperty("")
    public String getCurrencyRate() {
        return this.currencyRate;
    }

    public void setCurrencyRate(String currencyRate) {
        this.currencyRate = currencyRate;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Amount amount = (Amount)o;
            return Objects.equals(this.amount, amount.amount) && Objects.equals(this.currency, amount.currency) && Objects.equals(this.currencyRate, amount.currencyRate);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.amount, this.currency, this.currencyRate});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Amount {\n");
        sb.append("    amount: ").append(this.toIndentedString(this.amount)).append("\n");
        sb.append("    currency: ").append(this.toIndentedString(this.currency)).append("\n");
        sb.append("    currencyRate: ").append(this.toIndentedString(this.currencyRate)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
