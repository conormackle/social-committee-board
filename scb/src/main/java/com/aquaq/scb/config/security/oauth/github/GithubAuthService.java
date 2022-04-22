package com.aquaq.scb.config.security.oauth.github;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthService;
import com.aquaq.scb.entities.users.UsersService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.stereotype.Service;

@Service
public class GithubAuthService extends OAuthService {

    private UsersService usersService;
    private final String tokenPrefix;

    public GithubAuthService(UsersService usersService) {
        super(usersService);
        tokenPrefix = "token";
        super.setOauthProvider("Github");
    }

    @Override
    public UserData getUser(String tokenValue) throws JsonProcessingException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(Constants.GITHUB_USER_URI)
                .setHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-us")
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, tokenPrefix + " " + tokenValue)
                .build();
        String httpResponse = getHttpResponse(request);
        ObjectMapper mapper = new ObjectMapper();
        UserData userData = mapper.readValue(httpResponse, UserData.class);
        String email = getUserEmail(tokenValue);
        userData.setEmail(email);
        return userData;
    }

    public String getEmail(Object userData) {
        return ((UserData) userData).getEmail();
    }

    private String getUserEmail(String tokenValue) throws JsonProcessingException {
        HttpUriRequest request = RequestBuilder.get()
                .setUri(Constants.GITHUB_USER_EMAILS_URI)
                .setHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-us")
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, tokenPrefix + " " + tokenValue)
                .build();
        String httpResponse = getHttpResponse(request);
        String email = null;
        ObjectMapper mapper = new ObjectMapper();
        EmailData[] emailDataArray = mapper.readValue(httpResponse, EmailData[].class);
        for (EmailData emailData : emailDataArray) {
            if (emailData.isPrimary()) {
                email = emailData.getEmail();
                break;
            }
        }
        return email;
    }

}
