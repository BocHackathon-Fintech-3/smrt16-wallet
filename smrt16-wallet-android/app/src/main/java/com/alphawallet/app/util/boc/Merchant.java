package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Merchant {
    @SerializedName("name")
    private String name = null;
    @SerializedName("address")
    private PostalAddress address = null;

    public Merchant() {
    }

    public Merchant name(String name) {
        this.name = name;
        return this;
    }

//    @ApiModelProperty("")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Merchant address(PostalAddress address) {
        this.address = address;
        return this;
    }

//    @ApiModelProperty("")
    public PostalAddress getAddress() {
        return this.address;
    }

    public void setAddress(PostalAddress address) {
        this.address = address;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Merchant merchant = (Merchant)o;
            return Objects.equals(this.name, merchant.name) && Objects.equals(this.address, merchant.address);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.name, this.address});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Merchant {\n");
        sb.append("    name: ").append(this.toIndentedString(this.name)).append("\n");
        sb.append("    address: ").append(this.toIndentedString(this.address)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
