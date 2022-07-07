package com.aquaq.scb.config.security.oauth.zoho;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseBody {
    @JsonAlias("access_token")
    String accessToken;
    @JsonAlias("refresh_token")
    String refreshToken;
    @JsonAlias("expires_in")
    String expiresIn;
    @JsonAlias("api_domain")
    String apiDomain;
    @JsonAlias("token_type")
    String tokenType;
    @JsonAlias("error")
    String error;
    @JsonAlias("First_Name")
    private String firstName;
    @JsonAlias("Last_Name")
    private String lastName;
    @JsonAlias("Email")
    private String email;
    @JsonAlias("Display_Name")
    private String displayName;
    @JsonAlias("ZUID")
    private String zuid;
    private boolean isAquaQUser;
    private int userId;
    private boolean emailVerified;
}
