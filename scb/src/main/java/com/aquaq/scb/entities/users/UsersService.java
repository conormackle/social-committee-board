package com.aquaq.scb.entities.users;

import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.FeatureDescriptor;
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

    public ScbResponse getByUserId(Integer userId){
        try{
            Optional<UsersModel> usersModels = usersRepository.findById(userId);
            if(usersModels.isPresent()){
                return ScbResponse.createSuccessResponse(usersModels);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No user found for ID: %s", userId));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse createUser(UsersModel usersModel){
        try {
           UsersModel savedUsersModel = usersRepository.save(usersModel);
           return ScbResponse.createSuccessResponse(savedUsersModel);
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
