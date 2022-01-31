package com.aquaq.scb.entities.users;

import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return ScbResponse.createSuccessResponse(usersRepository.getById(userId));
        }catch(Exception e){
            return ScbResponse.createFailedResponse(e);
        }
    }
}
