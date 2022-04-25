package com.aquaq.scb.entities.roles;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.posts.PostsModel;
import com.aquaq.scb.entities.posts.PostsRepository;
import com.aquaq.scb.response.ScbResponse;
import com.aquaq.scb.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public RolesService(RolesRepository rolesRepository, ModelPropertyMapper modelPropertyMapper) {
        this.rolesRepository = rolesRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<RolesModel>> models = Optional.of(rolesRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<RolesModel> model = rolesRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(RolesModel model){
        try {
            return ScbResponse.createSuccessResponse(rolesRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(RolesModel model, int id){
        try {

            RolesModel updatedModel;
            Optional<RolesModel> savedModel = rolesRepository.findById(id);
            if(savedModel.isPresent()){
                RolesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = rolesRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
