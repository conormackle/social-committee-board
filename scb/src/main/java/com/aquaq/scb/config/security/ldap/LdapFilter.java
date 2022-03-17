//package com.aquaq.scb.config.security.ldap;
//
//import com.aquaq.scb.config.security.oauth.Constants;
//import com.nimbusds.oauth2.sdk.util.StringUtils;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class LdapFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//
//        String xAuth = request.getHeader(Constants.HEADER_NAME);
//        if (StringUtils.isBlank(xAuth)) {
//            filterChain.doFilter(request, response);
//        } else {
//            try {
//                LdapTokenHolder holder = new LdapTokenHolder(xAuth);
//
//                Authentication auth = new LdapAuthenticationToken(holder);
//
//                SecurityContextHolder.getContext().setAuthentication(auth);
//
//                filterChain.doFilter(request, response);
//            } catch (LdapTokenInvalidException e) {
//                throw new SecurityException(e);
//            }
//        }
//    }
//}
