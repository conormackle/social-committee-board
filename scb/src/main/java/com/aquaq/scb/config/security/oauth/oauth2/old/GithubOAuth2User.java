package com.aquaq.scb.config.security.oauth.oauth2.old;
import com.aquaq.scb.config.security.oauth.github.GithubAuthService;
import com.aquaq.scb.config.security.oauth.oauth2.OAuth2Exception;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import java.util.Collection;
import java.util.Map;

public class GithubOAuth2User implements OAuth2User {

    private OAuth2User oauth2User;

    private OAuth2UserRequest userRequest;

    private GithubAuthService githubAuthService;

    public GithubOAuth2User(OAuth2User oauth2User, OAuth2UserRequest userRequest, GithubAuthService githubAuthService) {;
        this.oauth2User = oauth2User;
        this.userRequest = userRequest;
        this.githubAuthService = githubAuthService;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return oauth2User.getAttributes();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() throws OAuth2Exception {
        return null;
//        return githubAuthService.getAuthorities(userRequest.getAccessToken().getTokenValue());
    }

    @Override
    public String getName() {
        return oauth2User.getAttribute("name");
    }

}
