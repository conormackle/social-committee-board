package com.aquaq.scb.entities.users;

import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
}
