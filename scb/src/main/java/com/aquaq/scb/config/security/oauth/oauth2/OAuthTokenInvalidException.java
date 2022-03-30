package com.aquaq.scb.config.security.oauth.oauth2;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.BadCredentialsException;

@Log4j2
public class OAuthTokenInvalidException extends BadCredentialsException {

    public OAuthTokenInvalidException(String msg) {
        super(msg);
        log.error(msg);
    }
    private static final long serialVersionUID = 789949671713648425L;
}