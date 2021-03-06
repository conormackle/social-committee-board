package com.aquaq.scb.config.security;

import com.aquaq.scb.config.security.oauth.oauth2.OAuthAuthenticationProvider;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import static com.aquaq.scb.config.security.oauth.Constants.*;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Configuration
    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

        private final OAuthAuthenticationProvider oAuthAuthenticationProvider;

        @Autowired
        public AuthenticationSecurity(OAuthAuthenticationProvider oAuthAuthenticationProvider) {
            this.oAuthAuthenticationProvider = oAuthAuthenticationProvider;
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(oAuthAuthenticationProvider);
        }
    }

    private OAuthFilter tokenAuthorizationFilter() {
        return new OAuthFilter();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class)
                .authorizeRequests()
//                .antMatchers("/auth/test").hasRole(ADMIN_ROLE)
                .antMatchers("/auth/test").authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf()
                .disable();
    }

}