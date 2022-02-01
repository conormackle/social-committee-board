package com.aquaq.scb.entities.projects;


import com.aquaq.scb.entities.users.UsersService;
import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/projects")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getProjects() {
        return projectsService.getProjects();
    }

    @GetMapping("/projects/getByProjectId/{projectId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getByProjectId(@PathVariable Integer projectId) {
        return projectsService.getByProjectId(projectId);
    }
}
