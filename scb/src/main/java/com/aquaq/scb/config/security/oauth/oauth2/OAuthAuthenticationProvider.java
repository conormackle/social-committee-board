package com.aquaq.scb.config.security.oauth.oauth2;

import com.aquaq.scb.config.security.oauth.github.GithubAuthService;
import com.aquaq.scb.config.security.oauth.zoho.ZohoAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class OAuthAuthenticationProvider implements AuthenticationProvider {

    private final GithubAuthService githubAuthService;
    private final ZohoAuthService zohoAuthService;

    @Autowired
    public OAuthAuthenticationProvider(GithubAuthService githubAuthService, ZohoAuthService zohoAuthService){
        this.githubAuthService = githubAuthService;
        this.zohoAuthService = zohoAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        OAuthAuthenticationToken authenticationToken;

        OAuthTokenHolder oAuthTokenHolder = (OAuthTokenHolder) authentication.getCredentials();

        authenticationToken = getOAuthToken(oAuthTokenHolder);

        return authenticationToken;
    }

    private OAuthAuthenticationToken getOAuthToken(OAuthTokenHolder oAuthTokenHolder){
        String token = oAuthTokenHolder.getToken();
        Object userData = null;
        String email;
        List<GrantedAuthority> grantedAuthorities = null;
        try {
            if (token.contains("zoho")) {
                userData = zohoAuthService.getUser(token.replace("zoho","").trim());
                email = zohoAuthService.getEmail(userData);
                grantedAuthorities = zohoAuthService.getAuthorities(email);
            }else if(token.contains("github")){
                userData = githubAuthService.getUser(token.replace("github","").trim());
                email = githubAuthService.getEmail(userData);
                grantedAuthorities = githubAuthService.getAuthorities(email);
            }
        }catch(JsonProcessingException exception){
            log.error(exception);
        }

        return new OAuthAuthenticationToken(userData, token,
                grantedAuthorities);
    }

    public boolean supports(Class<?> authentication) {
        return (OAuthAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
