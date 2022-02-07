package com.aquaq.scb.entities.events;


import com.aquaq.scb.entities.roles.RolesModel;
import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Events"})
public class EventsController {

    final
    EventsService eventsService;

    @Autowired
    public EventsController(EventsService eventsService) {
        this.eventsService = eventsService;
    }

    @GetMapping("/events")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getEvents() {
        return eventsService.getAll();
    }

    @GetMapping("/events/getByEventId/{eventId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getByEventId(@PathVariable Integer eventId) {
        return eventsService.getById(eventId);
    }

    @PutMapping("/events/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody EventsModel model, @PathVariable Integer id) {
        return eventsService.update(model, id);
    }

    @PostMapping("/events/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody EventsModel model) {
        return eventsService.create(model);
    }

}
