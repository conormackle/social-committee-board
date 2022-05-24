package com.aquaq.scb.entities.posts;

import com.aquaq.scb.response.ScbResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@Api(tags = {"Posts"})
public class PostsController {
    final
    PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/posts/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return postsService.getById(id);
    }

    @PostMapping("/posts/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody PostsModel model) {
        return postsService.create(model);
    }

    @GetMapping("/posts/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return postsService.getAll();
    }

    @PutMapping("/posts/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody PostsModel model,  @PathVariable Integer id) {
        return postsService.update(model, id);
    }

    @GetMapping("/posts/findByDate/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse findByDate(@RequestParam(required = false, name="startDate") String startDate,
                                  @RequestParam("endDate") String endDate,
                                  @RequestParam(required = false, name="page") Integer page,
                                  @RequestParam(required = false, name="size") Integer size) {
        return postsService.findByDate(startDate, endDate, page, size);
    }

    @GetMapping("/posts/findByCreatedDate/")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse findByCreatedDateTime(@RequestParam(required = false, name="startDate") String startDate,
                                             @RequestParam("endDate") String endDate,
                                             @RequestParam(required = false, name="page") Integer page,
                                             @RequestParam(required = false, name="size") Integer size) {
        return postsService.findByCreatedDateTime(startDate, endDate, page, size);
    }
}
