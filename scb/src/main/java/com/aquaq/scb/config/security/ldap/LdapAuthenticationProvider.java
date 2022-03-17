//package com.aquaq.scb.config.security.ldap;
//
//import com.aquaq.scb.config.security.oauth.Constants;
//import com.aquaq.scb.config.security.oauth.oauth2.OAuth2Exception;
//import com.aquaq.scb.entities.roles.RolesModel;
//import com.aquaq.scb.entities.users.UsersModel;
//import com.aquaq.scb.entities.users.UsersRepository;
//import com.google.gson.Gson;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.core.env.Environment;
//import org.springframework.ldap.core.LdapTemplate;
//import org.springframework.ldap.core.support.LdapContextSource;
//import org.springframework.ldap.filter.EqualsFilter;
//import org.springframework.ldap.filter.Filter;
//import org.springframework.ldap.support.LdapUtils;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Component;
//
//import java.io.UnsupportedEncodingException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Set;
//
//@Component
//public class LdapAuthenticationProvider implements AuthenticationProvider
//{
//    private Environment environment;
//
//    private LdapTemplate ldapTemplate;
//
//    private UsersRepository usersRepository;
//
//    public LdapAuthenticationProvider(Environment environment, UsersRepository usersRepository) {
//        this.environment = environment;
//        this.usersRepository = usersRepository;
//    }
//
//
//    public List<GrantedAuthority> getAuthorities(UserData userData) throws OAuth2Exception {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        UsersModel usersModel = usersRepository.getByEmail(userData.getEmail() != null ? userData.getEmail() : "");
//        if (usersModel != null) {
//            Set<RolesModel> usersRoles = usersModel.getRoles();
//            for (RolesModel rolesModel : usersRoles) {
//                grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE + rolesModel.getName().toUpperCase()));
//            }
//        } else {
//            throw new OAuth2Exception("User does not exist in the database!");
//        }
//        return grantedAuthorities;
//    }
//
//
//    private void initContext()
//    {
//        LdapContextSource contextSource = new LdapContextSource();
//        contextSource.setUrl(environment.getProperty("ldap.server.url"));
//        contextSource.setAnonymousReadOnly(true);
//        contextSource.setUserDn("uid={0},ou=people");
//        //contextSource.setBase("ou=groups");
//        contextSource.afterPropertiesSet();
//
//        ldapTemplate = new LdapTemplate(contextSource);
//    }
//
////    @Override
////    public Authentication authenticate(Authentication authentication) throws AuthenticationException
////    {
////        initContext();
////        Filter filter = new EqualsFilter("uid", authentication.getName());
////        boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(), authentication.getCredentials().toString());
////        if (authenticate) {
////            UserDetails userDetails = new User(authentication.getName(), authentication.getCredentials().toString()
////                    , new ArrayList<>());
////            return new UsernamePasswordAuthenticationToken(userDetails,
////                    authentication.getCredentials().toString(), new ArrayList<>());
////        } else {
////            return null;
////        }
////    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        initContext();
//        Filter filter = new EqualsFilter("uid", authentication.getName());
//        boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(), authentication.getCredentials().toString());
//
//        if (!supports(authentication.getClass()) || !authenticate) {
//            return null;
//        }
//
//        LdapAuthenticationToken authenticationToken;
//
////        LdapTokenHolder tokenHolder = (LdapTokenHolder) authentication.getCredentials();
//
////        UserData userData = getUser(authentication.getCredentials().toString());
//
//        UserData userData = new UserData();
//        userData.setEmail("joe.bloggs@aquaq.co.uk");
//
////        List<GrantedAuthority> grantedAuthorities = getAuthorities(userData);
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//        grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE + "test_role"));
//        userData.setAuthorities(grantedAuthorities);
//
//
//        authenticationToken = new LdapAuthenticationToken(userData, authentication.getCredentials(),
//                grantedAuthorities);
//
//        return authenticationToken;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication)
//    {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
