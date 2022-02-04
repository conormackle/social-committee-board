package com.aquaq.scb.entities.audits;

import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersService;
import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Audits"})
public class AuditsController {

    final
    AuditsService auditsService;

    @Autowired
    public AuditsController(AuditsService auditsService) {
        this.auditsService = auditsService;
    }

    @GetMapping("/audits/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return auditsService.getById(id);
    }

    @GetMapping("/audits/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return auditsService.getAll();
    }

}
