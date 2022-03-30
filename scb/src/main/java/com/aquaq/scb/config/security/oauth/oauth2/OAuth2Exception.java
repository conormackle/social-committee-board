package com.aquaq.scb.config.security.oauth.oauth2;

import org.springframework.security.core.AuthenticationException;

public class OAuth2Exception extends AuthenticationException {

    public OAuth2Exception(String message) {
        super(message);
    }
}
