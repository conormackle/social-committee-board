package com.aquaq.scb.config.security.oauth.oauth2.old;

import com.aquaq.scb.config.security.oauth.github.GithubAuthService;
import com.aquaq.scb.config.security.oauth.oauth2.OAuth2Exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class GithubOAuth2UserService extends DefaultOAuth2UserService {

    private final GithubAuthService githubAuthService;

    @Autowired
    public GithubOAuth2UserService(GithubAuthService githubAuthService){
        this.githubAuthService = githubAuthService;
    }

    @Override
    public GithubOAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException, OAuth2Exception {
        OAuth2User user =  super.loadUser(userRequest);
        return new GithubOAuth2User(user, userRequest, githubAuthService);
    }

}
