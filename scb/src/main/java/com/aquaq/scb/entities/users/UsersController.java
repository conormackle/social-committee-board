package com.aquaq.scb.entities.users;

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

    @Autowired
    public UsersController(UsersService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        try{
            return userService.getById(id);
        }catch(Exception e){
            System.out.println(e);
            ScbResponse scbResponse = new ScbResponse();
            scbResponse.setResponse(e);
            return scbResponse;
        }
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
//        return userService.delete(id);
        return null;
    }

}
