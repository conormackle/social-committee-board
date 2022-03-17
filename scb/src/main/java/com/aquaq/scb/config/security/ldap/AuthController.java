package com.aquaq.scb.config.security.ldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    LdapAuthenticationProvider ldapAuthenticationProvider;
    @Autowired
    private TokenManager tokenManager;
    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody JwtRequestModel
                                                request) throws Exception {
        try {
            ldapAuthenticationProvider.authenticate(request.getUsername(), request.getPassword());
        } catch (DisabledException e) {
            return new ResponseEntity<>("USER_DISABLED", HttpStatus.UNAUTHORIZED);
        } catch (LdapAuthenticationException e) {
            return new ResponseEntity<>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
}
