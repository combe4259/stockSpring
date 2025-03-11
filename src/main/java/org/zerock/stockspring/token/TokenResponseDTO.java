package org.zerock.stockspring.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TokenResponseDTO {
    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("access_token_token_expired")
    private String accessTokenExpired;

    public String getAccessToken(){
        return accessToken;
    }
    public String getAccessTokenExpired(){
        return accessTokenExpired;
    }
}
