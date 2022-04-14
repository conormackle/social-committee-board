package com.aquaq.scb.config.security.oauth.zoho;

import com.aquaq.scb.config.security.oauth.oauth2.OAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(tags = {"AuthController"})
public class AuthController {

    private ZohoAuthService oAuthService;

    @Autowired
    public AuthController(ZohoAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @GetMapping("/auth/test")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public String testAuth() {
        return "Success";
    }

    @GetMapping("/auth/getRefreshToken/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseBody getRefreshToken(@RequestParam("refreshToken") String refreshToken) throws JsonProcessingException {
        return oAuthService.getRefreshToken(refreshToken);
    }

    @GetMapping("/auth/authenticateUser/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ResponseBody authenticateUser(@RequestParam("code") String code) throws JsonProcessingException {
        return oAuthService.authenticateUser(code);
    }

}
