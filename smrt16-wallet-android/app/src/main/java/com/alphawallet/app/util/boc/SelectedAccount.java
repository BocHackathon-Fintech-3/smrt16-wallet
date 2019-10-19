package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class SelectedAccount {
    @SerializedName("accountId")
    private String accountId = null;

    public SelectedAccount() {
    }

    public SelectedAccount accountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            SelectedAccount selectedAccount = (SelectedAccount)o;
            return Objects.equals(this.accountId, selectedAccount.accountId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.accountId});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class SelectedAccount {\n");
        sb.append("    accountId: ").append(this.toIndentedString(this.accountId)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
