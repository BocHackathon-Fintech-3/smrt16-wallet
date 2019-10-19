package com.alphawallet.app.util.boc;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class IntrospectResponse {
    @SerializedName("active")
    private Boolean active = null;
    @SerializedName("client_id")
    private String clientId = null;
    @SerializedName("client_name")
    private String clientName = null;
    @SerializedName("username")
    private String username = null;
    @SerializedName("sub")
    private String sub = null;
    @SerializedName("exp")
    private String exp = null;
    @SerializedName("expstr")
    private String expstr = null;
    @SerializedName("iat")
    private String iat = null;
    @SerializedName("nbf")
    private String nbf = null;
    @SerializedName("nbfstr")
    private String nbfstr = null;
    @SerializedName("scope")
    private String scope = null;
    @SerializedName("miscinfo")
    private String miscinfo = null;
    @SerializedName("consented_on")
    private String consentedOn = null;
    @SerializedName("consented_on_str")
    private String consentedOnStr = null;
    @SerializedName("grant_type")
    private String grantType = null;

    public IntrospectResponse() {
    }

    public IntrospectResponse active(Boolean active) {
        this.active = active;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public Boolean isActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public IntrospectResponse clientId(String clientId) {
        this.clientId = clientId;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getClientId() {
        return this.clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public IntrospectResponse clientName(String clientName) {
        this.clientName = clientName;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public IntrospectResponse username(String username) {
        this.username = username;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public IntrospectResponse sub(String sub) {
        this.sub = sub;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getSub() {
        return this.sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public IntrospectResponse exp(String exp) {
        this.exp = exp;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getExp() {
        return this.exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public IntrospectResponse expstr(String expstr) {
        this.expstr = expstr;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getExpstr() {
        return this.expstr;
    }

    public void setExpstr(String expstr) {
        this.expstr = expstr;
    }

    public IntrospectResponse iat(String iat) {
        this.iat = iat;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getIat() {
        return this.iat;
    }

    public void setIat(String iat) {
        this.iat = iat;
    }

    public IntrospectResponse nbf(String nbf) {
        this.nbf = nbf;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getNbf() {
        return this.nbf;
    }

    public void setNbf(String nbf) {
        this.nbf = nbf;
    }

    public IntrospectResponse nbfstr(String nbfstr) {
        this.nbfstr = nbfstr;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getNbfstr() {
        return this.nbfstr;
    }

    public void setNbfstr(String nbfstr) {
        this.nbfstr = nbfstr;
    }

    public IntrospectResponse scope(String scope) {
        this.scope = scope;
        return this;
    }

//    @ApiModelProperty(
//            required = true,
//            value = ""
//    )
    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public IntrospectResponse miscinfo(String miscinfo) {
        this.miscinfo = miscinfo;
        return this;
    }

//    @ApiModelProperty("")
    public String getMiscinfo() {
        return this.miscinfo;
    }

    public void setMiscinfo(String miscinfo) {
        this.miscinfo = miscinfo;
    }

    public IntrospectResponse consentedOn(String consentedOn) {
        this.consentedOn = consentedOn;
        return this;
    }

//    @ApiModelProperty("")
    public String getConsentedOn() {
        return this.consentedOn;
    }

    public void setConsentedOn(String consentedOn) {
        this.consentedOn = consentedOn;
    }

    public IntrospectResponse consentedOnStr(String consentedOnStr) {
        this.consentedOnStr = consentedOnStr;
        return this;
    }

//    @ApiModelProperty("")
    public String getConsentedOnStr() {
        return this.consentedOnStr;
    }

    public void setConsentedOnStr(String consentedOnStr) {
        this.consentedOnStr = consentedOnStr;
    }

    public IntrospectResponse grantType(String grantType) {
        this.grantType = grantType;
        return this;
    }

//    @ApiModelProperty("")
    public String getGrantType() {
        return this.grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            IntrospectResponse introspectResponse = (IntrospectResponse)o;
            return Objects.equals(this.active, introspectResponse.active) && Objects.equals(this.clientId, introspectResponse.clientId) && Objects.equals(this.clientName, introspectResponse.clientName) && Objects.equals(this.username, introspectResponse.username) && Objects.equals(this.sub, introspectResponse.sub) && Objects.equals(this.exp, introspectResponse.exp) && Objects.equals(this.expstr, introspectResponse.expstr) && Objects.equals(this.iat, introspectResponse.iat) && Objects.equals(this.nbf, introspectResponse.nbf) && Objects.equals(this.nbfstr, introspectResponse.nbfstr) && Objects.equals(this.scope, introspectResponse.scope) && Objects.equals(this.miscinfo, introspectResponse.miscinfo) && Objects.equals(this.consentedOn, introspectResponse.consentedOn) && Objects.equals(this.consentedOnStr, introspectResponse.consentedOnStr) && Objects.equals(this.grantType, introspectResponse.grantType);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.active, this.clientId, this.clientName, this.username, this.sub, this.exp, this.expstr, this.iat, this.nbf, this.nbfstr, this.scope, this.miscinfo, this.consentedOn, this.consentedOnStr, this.grantType});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class IntrospectResponse {\n");
        sb.append("    active: ").append(this.toIndentedString(this.active)).append("\n");
        sb.append("    clientId: ").append(this.toIndentedString(this.clientId)).append("\n");
        sb.append("    clientName: ").append(this.toIndentedString(this.clientName)).append("\n");
        sb.append("    username: ").append(this.toIndentedString(this.username)).append("\n");
        sb.append("    sub: ").append(this.toIndentedString(this.sub)).append("\n");
        sb.append("    exp: ").append(this.toIndentedString(this.exp)).append("\n");
        sb.append("    expstr: ").append(this.toIndentedString(this.expstr)).append("\n");
        sb.append("    iat: ").append(this.toIndentedString(this.iat)).append("\n");
        sb.append("    nbf: ").append(this.toIndentedString(this.nbf)).append("\n");
        sb.append("    nbfstr: ").append(this.toIndentedString(this.nbfstr)).append("\n");
        sb.append("    scope: ").append(this.toIndentedString(this.scope)).append("\n");
        sb.append("    miscinfo: ").append(this.toIndentedString(this.miscinfo)).append("\n");
        sb.append("    consentedOn: ").append(this.toIndentedString(this.consentedOn)).append("\n");
        sb.append("    consentedOnStr: ").append(this.toIndentedString(this.consentedOnStr)).append("\n");
        sb.append("    grantType: ").append(this.toIndentedString(this.grantType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }
}
