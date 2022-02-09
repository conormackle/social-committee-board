package com.aquaq.scb.config.security.oauth.github;

import com.aquaq.scb.config.security.oauth.oauth2.OAuthAuthenticationToken;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthTokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GithubAuthenticationProvider implements AuthenticationProvider {

    private final GithubAuthService githubAuthService;

    @Autowired
    public GithubAuthenticationProvider(GithubAuthService githubAuthService){
        this.githubAuthService = githubAuthService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }
        OAuthAuthenticationToken authenticationToken;

        OAuthTokenHolder oAuthTokenHolder = (OAuthTokenHolder) authentication.getCredentials();

        UserData userData = githubAuthService.getUser(oAuthTokenHolder.getToken());

        List<GrantedAuthority> grantedAuthorities = githubAuthService.getAuthorities(userData);

        authenticationToken = new OAuthAuthenticationToken(userData, authentication.getCredentials(),
                grantedAuthorities);

        return authenticationToken;
    }

    public boolean supports(Class<?> authentication) {
        return (OAuthAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
