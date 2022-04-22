package com.aquaq.scb.config.security.oauth.zoho;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthService;
import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersService;
import com.aquaq.scb.utils.GeneralUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Log4j2
public class ZohoAuthService extends OAuthService {

    @Value("${zoho.auth.client.id}")
    private String clientId;
    @Value("${zoho.auth.client.secret}")
    private String secret;
    @Value("${zoho.auth.redirect.uri}")
    private String redirectUri;
    @Value("${zoho.auth.prompt}")
    private String prompt;
    @Value("${zoho.auth.access.type}")
    private String accessType;

    private UsersService usersService;
    private final String tokenPrefix;

    @Autowired
    public ZohoAuthService(UsersService usersService) {
        super(usersService);
        tokenPrefix = "Zoho-oauthtoken";
        super.setOauthProvider("Zoho");
    }

    @Override
    public ResponseBody getUser(String accessToken) throws JsonProcessingException {
        HttpUriRequest request = RequestBuilder.get()
            .setUri(Constants.ZOHO_USER_URI)
            .setHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-us")
            .setHeader(HttpHeaders.ACCEPT, "application/json")
            .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
            .setHeader(HttpHeaders.AUTHORIZATION, tokenPrefix+" "+accessToken)
                .build();
        String httpResponse = getHttpResponse(request);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(httpResponse, ResponseBody.class);
    }

    public ResponseBody getAccessToken(String code) throws JsonProcessingException {
        HttpUriRequest request = RequestBuilder.post()
                .setUri(Constants.ZOHO_TOKEN_URI)
                .setHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-us")
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
                .addParameter("grant_type", "authorization_code")
                .addParameter("client_id", clientId)
                .addParameter("client_secret", secret)
                .addParameter("redirect_uri", redirectUri)
                .addParameter("code", code)
                .addParameter("prompt", prompt)
                .addParameter("access_type", accessType)
                .addParameter("scope", "ZOHOPEOPLE.forms.ALL,Aaaserver.profile.read")
                .addParameter("response_type", "code")
                .build();
        String response = getHttpResponse(request);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, ResponseBody.class);
    }

    public ResponseBody getRefreshToken(String refreshToken) throws JsonProcessingException {
        HttpUriRequest request = RequestBuilder.post()
                .setUri(Constants.ZOHO_TOKEN_URI)
                .setHeader(HttpHeaders.ACCEPT_LANGUAGE, "en-us")
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
                .addParameter("grant_type", "refresh_token")
                .addParameter("client_id", clientId)
                .addParameter("client_secret", secret)
                .addParameter("redirect_uri", redirectUri)
                .addParameter("refresh_token", refreshToken)
                .build();
        String response = getHttpResponse(request);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, ResponseBody.class);
    }

    public ResponseBody authenticateUser(String code) throws JsonProcessingException {
        ResponseBody accessTokenResponse = getAccessToken(code);
        ResponseBody userResponse = getUser(accessTokenResponse.getAccessToken());
        String userEmail = userResponse.getEmail();
        GeneralUtils.copyProperties(accessTokenResponse, userResponse);
        if(userEmail.contains("@aquaq.co.uk")){
            userResponse.setAquaQUser(true);
            if(Objects.isNull(usersService.getByEmail(userEmail))){
                try {
                    UsersModel user = UsersModel.builder()
                            .email(userEmail)
                            .emailVerified(1)
                            .name(userResponse.getFirstName() + " " + userResponse.getLastName())
                            .build();
                    usersService.create(user);
                }catch(Exception exc){
                    log.error("ZohoAuthService - authenticateUser: Issue creating user for email: {}", userEmail);
                }
            }
        }
        return userResponse;
    }

}
