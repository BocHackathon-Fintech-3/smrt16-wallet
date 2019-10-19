package com.alphawallet.app.util.boc;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Objects;

public class AccessTokenResponse {
    @SerializedName("token_type")
    private AccessTokenResponse.TokenTypeEnum tokenType = null;
    @SerializedName("access_token")
    private String accessToken = null;
    @SerializedName("expires_in")
    private Integer expiresIn = null;
    @SerializedName("scope")
    private String scope = null;
    @SerializedName("refresh_token")
    private String refreshToken = null;

    public AccessTokenResponse() {
    }

    public AccessTokenResponse tokenType(AccessTokenResponse.TokenTypeEnum tokenType) {
        this.tokenType = tokenType;
        return this;
    }

//    @ApiModelProperty(
//            example = "bearer",
//            required = true,
//            value = ""
//    )
    public AccessTokenResponse.TokenTypeEnum getTokenType() {
        return this.tokenType;
    }

    public void setTokenType(AccessTokenResponse.TokenTypeEnum tokenType) {
        this.tokenType = tokenType;
    }

    public AccessTokenResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }
//
//    @ApiModelProperty(
//            example = "AAIkYTU5MzZjMzktOGEwZC00NWM0LWI1ZmUtNmE5NmZjNTZhMWIzA5iABduv03RDkzAaJMFgdDLTJ_CwGNxmfD5n_Kn4kc41sUY-S0bS8_gjVoVbkj4I0xNsKny1YycBUfD51RgQNE4jS5Ba1uvVdgRZ13hnekiC_Lt7fetOjWEk_GtdYfc0WaeEGvLctpnhOpOhRwm1FA",
//            required = true,
//            value = ""
//    )
    public String getAccessToken() {
        return this.accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessTokenResponse expiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

//    @ApiModelProperty(
//            example = "3600",
//            required = true,
//            value = ""
//    )
    public Integer getExpiresIn() {
        return this.expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public AccessTokenResponse scope(String scope) {
        this.scope = scope;
        return this;
    }
//
//    @ApiModelProperty(
//            example = "TPPOAuth2Security",
//            value = ""
//    )
    public String getScope() {
        return this.scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public AccessTokenResponse refreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
        return this;
    }

//    @ApiModelProperty("")
    public String getRefreshToken() {
        return this.refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            AccessTokenResponse accessTokenResponse = (AccessTokenResponse)o;
            return Objects.equals(this.tokenType, accessTokenResponse.tokenType) && Objects.equals(this.accessToken, accessTokenResponse.accessToken) && Objects.equals(this.expiresIn, accessTokenResponse.expiresIn) && Objects.equals(this.scope, accessTokenResponse.scope) && Objects.equals(this.refreshToken, accessTokenResponse.refreshToken);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.tokenType, this.accessToken, this.expiresIn, this.scope, this.refreshToken});
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AccessTokenResponse {\n");
        sb.append("    tokenType: ").append(this.toIndentedString(this.tokenType)).append("\n");
        sb.append("    accessToken: ").append(this.toIndentedString(this.accessToken)).append("\n");
        sb.append("    expiresIn: ").append(this.toIndentedString(this.expiresIn)).append("\n");
        sb.append("    scope: ").append(this.toIndentedString(this.scope)).append("\n");
        sb.append("    refreshToken: ").append(this.toIndentedString(this.refreshToken)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    private String toIndentedString(Object o) {
        return o == null ? "null" : o.toString().replace("\n", "\n    ");
    }

    @JsonAdapter(AccessTokenResponse.TokenTypeEnum.Adapter.class)
    public static enum TokenTypeEnum {
        BEARER("bearer");

        private String value;

        private TokenTypeEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }

        public String toString() {
            return String.valueOf(this.value);
        }

        public static AccessTokenResponse.TokenTypeEnum fromValue(String text) {
            AccessTokenResponse.TokenTypeEnum[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                AccessTokenResponse.TokenTypeEnum b = var1[var3];
                if (String.valueOf(b.value).equals(text)) {
                    return b;
                }
            }

            return null;
        }

        public static class Adapter extends TypeAdapter<TokenTypeEnum> {
            public Adapter() {
            }

            public void write(JsonWriter jsonWriter, AccessTokenResponse.TokenTypeEnum enumeration) throws IOException {
                jsonWriter.value(enumeration.getValue());
            }

            public AccessTokenResponse.TokenTypeEnum read(JsonReader jsonReader) throws IOException {
                String value = jsonReader.nextString();
                return AccessTokenResponse.TokenTypeEnum.fromValue(String.valueOf(value));
            }
        }
    }
}
