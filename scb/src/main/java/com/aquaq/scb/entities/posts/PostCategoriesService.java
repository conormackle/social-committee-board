package com.aquaq.scb.entities.posts;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class PostCategoriesService {

    private final PostCategoriesRepository postCategoriesRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public PostCategoriesService(PostCategoriesRepository postCategoriesRepository, ModelPropertyMapper modelPropertyMapper) {
        this.postCategoriesRepository = postCategoriesRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<PostCategoriesModel>> models = Optional.of(postCategoriesRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<PostCategoriesModel> model = postCategoriesRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id)));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(PostCategoriesModel model){
        try {
            return ScbResponse.createSuccessResponse(postCategoriesRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(PostCategoriesModel model, int id){
        try {

            PostCategoriesModel updatedModel;
            Optional<PostCategoriesModel> savedModel = postCategoriesRepository.findById(id);
            if(savedModel.isPresent()){
                PostCategoriesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = postCategoriesRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse("No entity found with ID: " + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
