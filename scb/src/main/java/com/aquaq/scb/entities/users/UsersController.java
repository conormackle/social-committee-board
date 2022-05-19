package com.aquaq.scb.entities.users;

import com.aquaq.scb.entities.users.projects.UserProjectsService;
import com.aquaq.scb.entities.users.roles.UserRolesService;
import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Users"})
public class UsersController {

    final
    UsersService userService;
    final
    UserProjectsService usersProjectsService;
    final
    UserRolesService userRolesService;

    @Autowired
    public UsersController(UsersService userService, UserProjectsService usersProjectsService, UserRolesService userRolesService) {
        this.userService = userService;
        this.usersProjectsService = usersProjectsService;
        this.userRolesService = userRolesService;
    }

    @GetMapping("/users/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @PostMapping("/users/create/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse createUser(@RequestBody UsersModel model) {
        return userService.create(model);
    }

    @GetMapping("/users/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return userService.getAll();
    }

    @PutMapping("/users/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody UsersModel model,  @PathVariable Integer id) {
        return userService.update(model, id);
    }

    @PutMapping("/users/delete/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse delete(@PathVariable Integer id) {
        return userService.delete(id);
    }

    @PostMapping("/users/addUserToRole/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse addUserToRole(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
        return userRolesService.addUserToRole(userId, roleId);
    }

    @DeleteMapping("/users/deleteUserFromRole/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse deleteUserFromRole(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
        return userRolesService.deleteUserFromRole(userId, roleId);
    }

    @PostMapping("/users/addUserToProject/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse addUserToProject(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
        return usersProjectsService.addUserToProject(userId, roleId);
    }

    @DeleteMapping("/users/deleteUserFromProject/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse deleteUserFromProject(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
        return usersProjectsService.deleteUserFromProject(userId, roleId);
    }

}
