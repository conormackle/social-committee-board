package com.aquaq.scb.config.security.oauth.zoho;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthService;
import com.aquaq.scb.entities.users.UsersRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZohoAuthService extends OAuthService {

    private UsersRepository usersRepository;

    @Autowired
    public ZohoAuthService(UsersRepository usersRepository) {
        super(usersRepository);
        super.setTokenPrefix("Zoho-oauthtoken");
        super.setOauthProvider("Zoho");
    }

    @Override
    public Object getUser(String tokenValue) throws JsonProcessingException {
        String httpResponse = getHttpResponse(tokenValue, Constants.ZOHO_USER_URI);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(httpResponse, UserData.class);
    }

    @Override
    public String getEmail(Object userData){
        return ((UserData) userData).getEmail();
    }

}
