package com.aquaq.scb.config.security.oauth.oauth2;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.entities.roles.RolesModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersRepository;
import com.aquaq.scb.http.CustomResponseHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public abstract class OAuthService {

    private String oauthProvider;
    private String tokenPrefix;

    private final UsersRepository usersRepository;

    protected void setOauthProvider(String oauthProvider){
        this.oauthProvider = oauthProvider;
    }

    protected void setTokenPrefix(String tokenPrefix){
        this.tokenPrefix = tokenPrefix;
    }

    @Autowired
    protected OAuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public abstract Object getUser(String tokenValue) throws JsonProcessingException;
    public abstract String getEmail(Object userData);

    public List<GrantedAuthority> getAuthorities(String email) throws OAuth2Exception {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        UsersModel usersModel = usersRepository.getByEmail(email != null ? email : "");
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

    protected String getHttpResponse(String tokenValue, String uri) {
        String httpResponse = httpResponseHandler(tokenValue, uri);
        if(httpResponse != null && httpResponse.equals(Constants.UNAUTHORIZED_CODE)){
            throw new OAuthTokenInvalidException("OAuth - "+oauthProvider+": Unauthorized access token");
        }
        return httpResponse;
    }

    private String httpResponseHandler(String tokenValue, String uri) {
        String httpResponse = null;
        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri(uri)
                .setHeader("Accept-Language", "en-us")
                .setHeader(HttpHeaders.ACCEPT, "application/json")
                .setHeader(HttpHeaders.ACCEPT_ENCODING, "application/json")
                .setHeader(HttpHeaders.AUTHORIZATION, tokenPrefix+" "+tokenValue)
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
