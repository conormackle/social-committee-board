package com.aquaq.scb.config.security;

import com.aquaq.scb.config.security.oauth.github.GithubAuthenticationProvider;
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

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Configuration
    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {

        private final GithubAuthenticationProvider oAuthAuthenticationProvider;

        @Autowired
        public AuthenticationSecurity(GithubAuthenticationProvider oAuthAuthenticationProvider) {
            this.oAuthAuthenticationProvider = oAuthAuthenticationProvider;
        }

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(oAuthAuthenticationProvider);
        }
    }

    private OAuthFilter tokenAuthorizationFilter() {
        return new OAuthFilter();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("*"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
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
                .antMatchers("/users/getAll").hasRole("ADMIN")
                .anyRequest().permitAll();
//                .and()
//                .oauth2Login();
    }

//    @Autowired
//    private GithubOAuth2UserService userService;

}