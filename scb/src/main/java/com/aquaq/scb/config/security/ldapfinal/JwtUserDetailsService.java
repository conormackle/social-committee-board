package com.aquaq.scb.config.security.ldapfinal;

import com.aquaq.scb.config.security.oauth.Constants;
import com.aquaq.scb.entities.roles.RolesModel;
import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final UsersRepository usersRepository;

    @Autowired
    public JwtUserDetailsService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UsersModel user = usersRepository.getByEmail(email != null ? email : "");

        if (user != null) {
            List<GrantedAuthority> grantedAuthorities = getAuthorities(user);
            return new User(email,
                    "null",
                    grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("User not found with username: " + email);
        }
    }

    public List<GrantedAuthority> getAuthorities(UsersModel user) throws UsernameNotFoundException {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            Set<RolesModel> usersRoles = user.getRoles();
            for (RolesModel rolesModel : usersRoles) {
                grantedAuthorities.add(new SimpleGrantedAuthority(Constants.ROLE + rolesModel.getName().toUpperCase()));
            }
        } else {
            throw new UsernameNotFoundException("User does not exist in the database!");
        }
        return grantedAuthorities;
    }

}