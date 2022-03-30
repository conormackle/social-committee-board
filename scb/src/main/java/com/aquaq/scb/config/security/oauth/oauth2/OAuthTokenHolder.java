package com.aquaq.scb.config.security.oauth.oauth2;

import lombok.Data;

@Data
public class OAuthTokenHolder {

    private String token;

    public OAuthTokenHolder(String tokenValue){
        this.token = tokenValue;
    }
}
