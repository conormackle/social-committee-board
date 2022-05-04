package com.aquaq.scb.entities.ZohoEntity;

import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@Api(tags = {"ZohoEntities"})
public class ZohoEntityController {

    final
    ZohoEntityService zohoEntityService;

    @Autowired
    public ZohoEntityController(ZohoEntityService zohoEntityService) {
        this.zohoEntityService = zohoEntityService;
    }

    @GetMapping("/thankyou/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAllThankYou(@RequestHeader("ZohoOAuthToken") String ZohoOAuthToken) {
        return zohoEntityService.getThankYous(ZohoOAuthToken);
    }

    @GetMapping("/announcement/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAllAnnouncement(@RequestHeader("ZohoOAuthToken") String ZohoOAuthToken) {
        return zohoEntityService.getAnnouncements(ZohoOAuthToken);
    }

}
