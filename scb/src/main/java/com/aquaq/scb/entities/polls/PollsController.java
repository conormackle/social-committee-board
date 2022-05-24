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
    public ScbResponse update(@RequestBody PollsModel model, @PathVariable Integer id) {
        return pollsService.update(model, id);
    }

    @GetMapping("/polls/findByDate/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse findByDate(@RequestParam(required = false, name = "startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam(required = false, name = "page") Integer page,
                                  @RequestParam(required = false, name = "size") Integer size) {
        return pollsService.findByDate(startDate, endDate, page, size);
    }

    @GetMapping("/polls/findByCreatedDate/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse findByCreatedDateTime(@RequestParam(required = false, name = "startDate") String startDate,
                                             @RequestParam("endDate") String endDate,
                                             @RequestParam(required = false, name = "page") Integer page,
                                             @RequestParam(required = false, name = "size") Integer size) {
        return pollsService.findByCreatedDateTime(startDate, endDate, page, size);
    }

    @PutMapping("/polls/addVote")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse addVote(@RequestBody PollOptionVoteModel pollOptionVoteModel) {
        return pollsService.addVote(pollOptionVoteModel);
    }

    @PutMapping("/polls/deleteVote")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse deleteVote(@RequestBody PollOptionVoteModel pollOptionVoteModel) {
        return pollsService.deleteVote(pollOptionVoteModel);
    }

}
