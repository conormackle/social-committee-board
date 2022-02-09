package com.aquaq.scb.config.security.oauth.github;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.config.security.oauth.oauth2.OAuth2Exception;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthTokenInvalidException;
import com.aquaq.scb.entities.roles.RolesModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersRepository;
import com.aquaq.scb.http.CustomResponseHandler;
import com.google.gson.Gson;
import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GithubAuthService {

    private UsersRepository usersRepository;

    @Autowired
    public GithubAuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<GrantedAuthority> getAuthorities(UserData userData) throws OAuth2Exception {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        UsersModel usersModel = usersRepository.getByEmail(userData.getEmail() != null ? userData.getEmail() : "");
        if (usersModel != null) {
            Set<RolesModel> usersRoles = usersModel.getRoles();
            for (RolesModel rolesModel : usersRoles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE + rolesModel.getName().toUpperCase()));
            }
        } else {
            throw new OAuth2Exception("User does not exist in the database!");
        }
        return grantedAuthorities;
    }

    public UserData getUser(String tokenValue) {
        String httpResponse = getUserDetailsHttpResponse(tokenValue, Constants.USER_URI);
        Gson gson = new Gson();
        if(httpResponse.equals(Constants.UNAUTHORIZED_CODE)){
            throw new OAuthTokenInvalidException("Github OAuth: Unauthorized access token");
        }
        UserData userData = gson.fromJson(httpResponse, UserData.class);
        String email = getUserEmail(tokenValue);
        userData.setEmail(email);
        return userData;
    }

    public String getUserEmail(String tokenValue) {
        String httpResponse = getUserDetailsHttpResponse(tokenValue, Constants.USER_EMAILS_URI);
        if(httpResponse.equals(Constants.UNAUTHORIZED_CODE)){
            throw new OAuthTokenInvalidException("Github OAuth: Unauthorized access token");
        }
        String email = null;
        Gson gson = new Gson();
        EmailData[] emailDataArray = gson.fromJson(httpResponse, EmailData[].class);
        for (EmailData emailData : emailDataArray) {
            if (emailData.isPrimary()) {
                email = emailData.getEmail();
                break;
            }
        }
        return email;
    }

    private String getUserDetailsHttpResponse(String tokenValue, String uri) {
        String httpResponse = null;
        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri(uri)
                .setHeader("Accept-Language", "en-us")
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, "token " + tokenValue)
                .build();
        try {
            ResponseHandler<String> responseHandler = new CustomResponseHandler();
            httpResponse = client.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }


}
