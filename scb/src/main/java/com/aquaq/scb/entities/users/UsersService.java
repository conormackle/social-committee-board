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

    @Autowired
    public UsersService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<UsersModel>> usersModels = Optional.of(usersRepository.findAll());
            return ScbResponse.createSuccessResponse(usersModels.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<UsersModel> usersModel = usersRepository.findById(id);
            return usersModel.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id)));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse createUser(UsersModel user){
        try {
            return ScbResponse.createSuccessResponse(usersRepository.save(user));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

//    public ScbResponse delete(int id){
//        try{
//
//            Optional<UsersModel> usersModel = usersRepository.findById(id);
//            usersModel.setDeleted(false);
//            usersRepository.save(UsersMapper.toUserModel(usersModel));
//            return ScbResponse.createSuccessResponse("Success");
//        }catch(Exception e){
//            return ScbResponse.createExceptionResponse(e);
//        }
//    }

    public ScbResponse update(UsersModel usersModel, int id){
        try {

            UsersModel updatedUsersModel;
            Optional<UsersModel> savedUsersModel = usersRepository.findById(id);
            if(savedUsersModel.isPresent()){
                UsersModel updateUser = savedUsersModel.get();
                copyModelProperties(usersModel, updateUser);
                updatedUsersModel = usersRepository.save(updateUser);
                return ScbResponse.createSuccessResponse(updatedUsersModel);
            }else{
                return ScbResponse.createSuccessResponse("No entity found with ID: " + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
