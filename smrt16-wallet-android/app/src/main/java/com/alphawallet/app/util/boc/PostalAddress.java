package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class PostalAddress {
    @SerializedName("line1")
    private String line1 = null;
    @SerializedName("line2")
    private String line2 = null;
    @SerializedName("line3")
    private String line3 = null;
    @SerializedName("line4")
    private String line4 = null;
    @SerializedName("city")
    private String city = null;
    @SerializedName("postalcode")
    private String postalcode = null;
    @SerializedName("state")
    private String state = null;
    @SerializedName("country")
    private String country = null;

    public PostalAddress() {
    }

    public PostalAddress line1(String line1) {
        this.line1 = line1;
        return this;
    }

//    @ApiModelProperty("")
    public String getLine1() {
        return this.line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public PostalAddress line2(String line2) {
        this.line2 = line2;
        return this;
    }

//    @ApiModelProperty("")
    public String getLine2() {
        return this.line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public PostalAddress line3(String line3) {
        this.line3 = line3;
        return this;
    }

//    @ApiModelProperty("")
    public String getLine3() {
        return this.line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public PostalAddress line4(String line4) {
        this.line4 = line4;
        return this;
    }

//    @ApiModelProperty("")
    public String getLine4() {
        return this.line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public PostalAddress city(String city) {
        this.city = city;
        return this;
    }

//    @ApiModelProperty("")
    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public PostalAddress postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

//    @ApiModelProperty("")
    public String getPostalcode() {
        return this.postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public PostalAddress state(String state) {
        this.state = state;
        return this;
    }

//    @ApiModelProperty("")
    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public PostalAddress country(String country) {
        this.country = country;
        return this;
    }

//    @ApiModelProperty("")
    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            PostalAddress postalAddress = (PostalAddress)o;
            return Objects.equals(this.line1, postalAddress.line1) && Objects.equals(this.line2, postalAddress.line2) && Objects.equals(this.line3, postalAddress.line3) && Objects.equals(this.line4, postalAddress.line4) && Objects.equals(this.city, postalAddress.city) && Objects.equals(this.postalcode, postalAddress.postalcode) && Objects.equals(this.state, postalAddress.state) && Objects.equals(this.country, postalAddress.country);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.line1, this.line2, this.line3, this.line4, this.city, this.postalcode, this.state, this.country});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class PostalAddress {\n");
        sb.append("    line1: ").append(this.toIndentedString(this.line1)).append("\n");
        sb.append("    line2: ").append(this.toIndentedString(this.line2)).append("\n");
        sb.append("    line3: ").append(this.toIndentedString(this.line3)).append("\n");
        sb.append("    line4: ").append(this.toIndentedString(this.line4)).append("\n");
        sb.append("    city: ").append(this.toIndentedString(this.city)).append("\n");
        sb.append("    postalcode: ").append(this.toIndentedString(this.postalcode)).append("\n");
        sb.append("    state: ").append(this.toIndentedString(this.state)).append("\n");
        sb.append("    country: ").append(this.toIndentedString(this.country)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}

