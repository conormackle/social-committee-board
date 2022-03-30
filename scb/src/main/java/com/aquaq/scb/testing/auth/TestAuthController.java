package com.aquaq.scb.testing.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"AuthTest"})
public class TestAuthController {

    @Autowired
    public TestAuthController() {

    }

    @GetMapping("/auth/test")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public String testGetAll() {
        return "Success";
    }

}
