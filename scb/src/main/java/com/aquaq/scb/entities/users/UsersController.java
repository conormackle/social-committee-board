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

    @GetMapping("/users/getById/{userId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer userId) {
        return userService.getById(userId);
    }

    @PostMapping("/users/createUser/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse createUser(@RequestBody UsersDto usersDto) {
        return userService.createUser(usersDto);
    }

    @GetMapping("/users/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return userService.getAll();
    }

    @PutMapping("/users/update")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody UsersDto requestDto) {
        return userService.update(requestDto);
    }

    @PutMapping("/users/delete/{userId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse delete(@PathVariable Integer userId) {
        return userService.delete(userId);
    }


    @PutMapping("/updateUser/{userId}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse updateUser(@RequestBody UsersModel usersModel,  @PathVariable Integer userId){
        ScbResponse usersModelResponse = userService.updateUser(usersModel, userId);
        return usersModelResponse;
    }
}
