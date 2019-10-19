package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Objects;

public class Balance {
    @SerializedName("amount")
    private BigDecimal amount = null;
    @SerializedName("balanceType")
    private String balanceType = null;

    public Balance() {
    }

    public Balance amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

//    @ApiModelProperty("")
    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Balance balanceType(String balanceType) {
        this.balanceType = balanceType;
        return this;
    }

//    @ApiModelProperty("")
    public String getBalanceType() {
        return this.balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Balance balance = (Balance)o;
            return Objects.equals(this.amount, balance.amount) && Objects.equals(this.balanceType, balance.balanceType);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.amount, this.balanceType});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Balance {\n");
        sb.append("    amount: ").append(this.toIndentedString(this.amount)).append("\n");
        sb.append("    balanceType: ").append(this.toIndentedString(this.balanceType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
