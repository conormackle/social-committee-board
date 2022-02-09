package com.aquaq.scb.config.security.oauth.oauth2;

import com.aquaq.scb.config.security.oauth.Constants;
import com.nimbusds.oauth2.sdk.util.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OAuthFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String xAuth = request.getHeader(Constants.HEADER_NAME);
        if (StringUtils.isBlank(xAuth)) {
            filterChain.doFilter(request, response);
            return;
        } else {
            try {
                OAuthTokenHolder holder = new OAuthTokenHolder(xAuth);

                Authentication auth = new OAuthAuthenticationToken(holder);

                SecurityContextHolder.getContext().setAuthentication(auth);

                filterChain.doFilter(request, response);
            } catch (OAuthTokenInvalidException e) {
                throw new SecurityException(e);
            }
        }
    }
}
