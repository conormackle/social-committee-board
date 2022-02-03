package com.aquaq.scb.entities.polls;

import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Polls"})
public class PollsController {
    final
    PollsService pollsService;

    @Autowired
    public PollsController(PollsService pollsService) {
        this.pollsService = pollsService;
    }

    @GetMapping("/polls/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return pollsService.getById(id);
    }

    @PostMapping("/polls/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody PollsModel model) {
        return pollsService.create(model);
    }

    @GetMapping("/polls/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return pollsService.getAll();
    }

    @PutMapping("/polls/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody PollsModel model,  @PathVariable Integer id) {
        return pollsService.update(model, id);
    }
}
