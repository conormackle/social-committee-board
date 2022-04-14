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
import org.springframework.beans.factory.annotation.Value;
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

    private final UsersRepository usersRepository;

    protected void setOauthProvider(String oauthProvider){
        this.oauthProvider = oauthProvider;
    }

    @Autowired
    protected OAuthService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public abstract Object getUser(String tokenValue) throws JsonProcessingException;

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

    protected String getHttpResponse(HttpUriRequest request) {
        String httpResponse = httpResponseHandler(request);
        if(httpResponse != null && httpResponse.equals(Constants.UNAUTHORIZED_CODE)){
            throw new OAuthTokenInvalidException("OAuth - "+oauthProvider+": Unauthorized access token");
        }
        return httpResponse;
    }

    private String httpResponseHandler(HttpUriRequest request) {
        String httpResponse = null;
        HttpClient client = HttpClients.custom().build();
        try {
            ResponseHandler<String> responseHandler = new CustomResponseHandler();
            httpResponse = client.execute(request, responseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return httpResponse;
    }
}
