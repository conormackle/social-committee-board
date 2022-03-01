package com.aquaq.scb.entities.events;

import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"EventCategories"})
public class EventCategoriesController {

    final
    EventCategoriesService eventCategoriesService;

    @Autowired
    public EventCategoriesController(EventCategoriesService eventCategoriesService) {
        this.eventCategoriesService = eventCategoriesService;
    }

    @GetMapping("/eventCategories/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return eventCategoriesService.getById(id);
    }

    @PostMapping("/eventCategories/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody EventCategoriesModel model) {
        return eventCategoriesService.create(model);
    }

    @GetMapping("/eventCategories/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return eventCategoriesService.getAll();
    }

    @PutMapping("/eventCategories/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody EventCategoriesModel model, @PathVariable Integer id) {
        return eventCategoriesService.update(model, id);
    }
}
