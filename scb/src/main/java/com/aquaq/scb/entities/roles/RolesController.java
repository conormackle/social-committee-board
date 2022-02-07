package com.aquaq.scb.entities.roles;

import com.aquaq.scb.entities.posts.PostsModel;
import com.aquaq.scb.entities.projects.ProjectsService;
import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Roles"})
public class RolesController {
    final
    RolesService rolesService;

    @Autowired
    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @GetMapping("/roles")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getProjects() {
        return rolesService.getAll();
    }

    @GetMapping("/roles/getbyId/{roleId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return rolesService.getById(id);
    }

    @PutMapping("/roles/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody RolesModel model, @PathVariable Integer id) {
        return rolesService.update(model, id);
    }

    @PostMapping("/roles/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody RolesModel model) {
        return rolesService.create(model);
    }

}
