package com.aquaq.scb.config.security.ldap;

import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.Filter;
import org.springframework.ldap.support.LdapUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class LdapAuthenticationProvider
{
    private Environment environment;

    private LdapTemplate ldapTemplate;

    public LdapAuthenticationProvider(Environment environment) {
        this.environment = environment;
    }

    private void initContext()
    {
        LdapContextSource contextSource = new LdapContextSource();
        contextSource.setUrl(environment.getProperty("ldap.server.url"));
        contextSource.setAnonymousReadOnly(true);
        contextSource.setUserDn("uid={0},ou=people");
        //contextSource.setBase("ou=groups");
        contextSource.afterPropertiesSet();

        ldapTemplate = new LdapTemplate(contextSource);
    }

    public Authentication authenticate(String username, String password) throws Exception
    {
        initContext();
        Filter filter = new EqualsFilter("uid", username);
        boolean authenticate = ldapTemplate.authenticate(LdapUtils.emptyLdapName(), filter.encode(), password);
        if (authenticate) {
            UserDetails userDetails = new User(username, password
                    , new ArrayList<>());
            return new UsernamePasswordAuthenticationToken(userDetails,
                    password, new ArrayList<>());
        } else {
            throw new Exception("User does not exist");
        }
    }
}
