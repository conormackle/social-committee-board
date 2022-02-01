package com.aquaq.scb.entities.users;

import com.aquaq.scb.entities.mapper.UsersMapper;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

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

    public ScbResponse getById(Integer userId){
        try{
            Optional<UsersModel> usersModel = usersRepository.findById(userId);
            if(usersModel.isPresent()){
                return ScbResponse.createSuccessResponse(usersModel);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No user found for ID: %s", userId));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse createUser(UsersDto usersDto){
        try {
            return ScbResponse.createSuccessResponse(usersRepository.save(UsersMapper.toUserModel(usersDto)));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
    public ScbResponse update(UsersDto usersDto){
        try{
            usersRepository.save(UsersMapper.toUserModel(usersDto));
            return ScbResponse.createSuccessResponse("Success");
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse delete(int userId){
        try{
//            Optional<UsersModel> usersModel = usersRepository.findById(userId);
//            usersModel.setDeleted(false);
//            usersRepository.save(UsersMapper.toUserModel(usersModel));
            return ScbResponse.createSuccessResponse("Success");
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse updateUser(UsersModel usersModel, Integer userId){
        try {
            UsersModel updatedUsersModel = new UsersModel();
            Optional<UsersModel> savedUsersModel = usersRepository.findById(userId);
            UsersModel updateUser = savedUsersModel.get();
            if(savedUsersModel.isPresent()){
                BeanUtils.copyProperties(usersModel, updateUser, getNullPropertyNames(usersModel));
            }
            updatedUsersModel = usersRepository.save(updateUser);
            return ScbResponse.createSuccessResponse(updatedUsersModel);
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper wrappedSource = new BeanWrapperImpl(source);
        return Stream.of(wrappedSource.getPropertyDescriptors())
                .map(FeatureDescriptor::getName)
                .filter(propertyName -> wrappedSource.getPropertyValue(propertyName) == null)
                .toArray(String[]::new);
    }
}
