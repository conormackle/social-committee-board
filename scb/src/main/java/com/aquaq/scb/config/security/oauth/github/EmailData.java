package com.aquaq.scb.config.security.oauth.github;

import lombok.Data;

@Data
public class EmailData {
    private String email;
    private boolean primary;
    private boolean verified;
    private boolean visibility;
}
