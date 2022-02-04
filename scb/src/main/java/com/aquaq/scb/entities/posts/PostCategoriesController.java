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
@Api(tags = {"PostCategories"})
public class PostCategoriesController {

    final
    PostCategoriesService postCategoriesService;

    @Autowired
    public PostCategoriesController(PostCategoriesService postCategoriesService) {
        this.postCategoriesService = postCategoriesService;
    }

    @GetMapping("/postCategories/getById/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getById(@PathVariable Integer id) {
        return postCategoriesService.getById(id);
    }

    @PostMapping("/postCategories/create")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse create(@RequestBody PostCategoriesModel model) {
        return postCategoriesService.create(model);
    }

    @GetMapping("/postCategories/getAll")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse getAll() {
        return postCategoriesService.getAll();
    }

    @PutMapping("/postCategories/update/{id}")
    @ApiOperation(value = "", authorizations = {@Authorization(value = "apiKey")})
    public ScbResponse update(@RequestBody PostCategoriesModel model,  @PathVariable Integer id) {
        return postCategoriesService.update(model, id);
    }
}
