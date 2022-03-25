package com.aquaq.scb.config.security.oauth.github;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthService;
import com.aquaq.scb.entities.users.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class GithubAuthService extends OAuthService {

    private UsersRepository usersRepository;

    public GithubAuthService(UsersRepository usersRepository) {
        super(usersRepository);
        super.setTokenPrefix("token");
        super.setOauthProvider("Github");
    }

    @Override
    public UserData getUser(String tokenValue) throws JsonProcessingException {
        String httpResponse = getHttpResponse(tokenValue, Constants.GITHUB_USER_URI);
        ObjectMapper mapper = new ObjectMapper();
        UserData userData = mapper.readValue(httpResponse, UserData.class);
        String email = getUserEmail(tokenValue);
        userData.setEmail(email);
        return userData;
    }

    @Override
    public String getEmail(Object userData){
        return ((UserData) userData).getEmail();
    }

    private String getUserEmail(String tokenValue) throws JsonProcessingException {
        String httpResponse = getHttpResponse(tokenValue, Constants.GITHUB_USER_EMAILS_URI);
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
