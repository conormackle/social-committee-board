package com.aquaq.scb.entities.projects;

import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Projects"})
public class ProjectsController {

    final
    ProjectsService projectsService;

    @Autowired
    public ProjectsController(ProjectsService projectsService) {
        this.projectsService = projectsService;
    }

    @GetMapping("/projects/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return projectsService.getAll();
    }

    @GetMapping("/projects/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return projectsService.getById(id);
    }

    @PostMapping("/projects/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody ProjectsModel model) {
        return projectsService.create(model);
    }

    @PutMapping("/projects/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody ProjectsModel model,  @PathVariable Integer id) {
        return projectsService.update(model, id);
    }
}
