package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.projects.ProjectsService;
import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.apache.tomcat.jni.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/polls/getByPollId/{pollId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getByPollId(@PathVariable Integer pollId) {
        return pollsService.getByPollId(pollId);
    }
}
