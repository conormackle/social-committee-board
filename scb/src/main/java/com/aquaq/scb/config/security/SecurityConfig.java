//package com.aquaq.scb.config.security;
//
//import com.aquaq.scb.config.security.ldap.JwtTokenProvider;
//import com.aquaq.scb.config.security.ldap.LdapAuthenticationProvider;
//import com.aquaq.scb.config.security.ldap.LdapFilter;
//import com.aquaq.scb.config.security.oauth.github.GithubAuthenticationProvider;
//import com.aquaq.scb.config.security.oauth.oauth2.OAuthFilter;
//import com.aquaq.scb.config.security.oauth.oauth2.old.GithubOAuth2UserService;
//import com.aquaq.scb.entities.users.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.Environment;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Configuration
//    protected static class AuthenticationSecurity extends GlobalAuthenticationConfigurerAdapter {
//
//        private final LdapAuthenticationProvider ldapAuthenticationProvider;
//
//        @Autowired
//        public AuthenticationSecurity(LdapAuthenticationProvider ldapAuthenticationProvider) {
//            this.ldapAuthenticationProvider = ldapAuthenticationProvider;
//        }
//
//        @Override
//        public void init(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(ldapAuthenticationProvider);
//        }
//    }
////
//    private LdapFilter tokenAuthorizationFilter() {
//        return new LdapFilter();
//    }
//
////    @Bean
////    CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("*"));
////        configuration.setAllowedMethods(Arrays.asList("*"));
////        configuration.setAllowedHeaders(Arrays.asList("*"));
////        configuration.setAllowCredentials(true);
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", configuration);
////        return source;
////    }
//
//    private Environment env;
//    private UsersRepository usersRepository;
//
//    public SecurityConfig(Environment env, UsersRepository usersRepository){
//        this.env = env;
//        this.usersRepository = usersRepository;
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception
//    {
//        auth.authenticationProvider(new LdapAuthenticationProvider(env, usersRepository)).eraseCredentials(false);
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity httpSecurity) throws Exception
//    {
//        httpSecurity
//                .addFilterBefore(tokenAuthorizationFilter(), BasicAuthenticationFilter.class)
//                .authorizeRequests()
//                .antMatchers("/auth-server").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf()
//                .disable()
//                .httpBasic();
//
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public JwtTokenProvider provider(){
//        return new JwtTokenProvider();
//    }
//
//}