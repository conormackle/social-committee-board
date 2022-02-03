package com.aquaq.scb.entities.users;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import static com.aquaq.scb.utils.GeneralUtils.copyModelProperties;

@Log4j2
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public UsersService(UsersRepository usersRepository, ModelPropertyMapper modelPropertyMapper){
        this.usersRepository = usersRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<UsersModel>> models = Optional.of(usersRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<UsersModel> model = usersRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id)));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(UsersModel model){
        try {
            return ScbResponse.createSuccessResponse(usersRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

//    public ScbResponse delete(int id){
//        try{
//
//            Optional<UsersModel> model = usersRepository.findById(id);
//            model.setDeleted(false);
//            usersRepository.save(model);
//            return ScbResponse.createSuccessResponse("Success");
//        }catch(Exception e){
//            return ScbResponse.createExceptionResponse(e);
//        }
//    }

    public ScbResponse update(UsersModel model, int id){
        try {

            UsersModel updatedModel;
            Optional<UsersModel> savedModel = usersRepository.findById(id);
            if(savedModel.isPresent()){
                UsersModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = usersRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse("No entity found with ID: " + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
