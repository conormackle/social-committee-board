package com.aquaq.scb.entities.events;


import com.aquaq.scb.entities.events.images.EventImagesModel;
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

    @GetMapping("/events/getAll")
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

    @PostMapping("/events/addImage/{eventId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse addImage(@RequestBody EventImagesModel model, @PathVariable Integer eventId) {
        return eventsService.addImage(model, eventId);
    }

    @PostMapping("/events/updateImage/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse updateImage(@RequestBody EventImagesModel model) {
        return eventsService.updateImage(model);
    }

    @GetMapping("/events/getThumbnail/{eventId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getThumbnail(@PathVariable Integer eventId) {
        return eventsService.getThumbnail(eventId);
    }

    @GetMapping("/events/findByDate/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse findByDate(@RequestParam(required = false, name = "startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam(required = false, name = "page") Integer page,
                                  @RequestParam(required = false, name = "size") Integer size) {
        return eventsService.findByDate(startDate, endDate, page, size);
    }

    @GetMapping("/events/findByCreatedDate/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse findByCreatedDateTime(@RequestParam(required = false, name = "startDate") String startDate,
                                             @RequestParam("endDate") String endDate,
                                             @RequestParam(required = false, name = "page") Integer page,
                                             @RequestParam(required = false, name = "size") Integer size) {
        return eventsService.findByCreatedDateTime(startDate, endDate, page, size);
    }

    @PutMapping("/events/addAttendee/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse addAttendee(@RequestBody EventAttendeeModel eventAttendeeModel) {
        return eventsService.addEventAttendee(eventAttendeeModel);
    }
}
