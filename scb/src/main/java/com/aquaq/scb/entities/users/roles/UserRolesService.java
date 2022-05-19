package com.aquaq.scb.entities.users.roles;

import com.aquaq.scb.entities.roles.RolesModel;
import com.aquaq.scb.entities.roles.RolesRepository;
import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersRepository;
import com.aquaq.scb.response.ScbResponse;
import com.aquaq.scb.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class UserRolesService {

    private final UsersRepository usersRepository;
    private final UserRolesRepository userRolesRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public UserRolesService(UsersRepository usersRepository, UserRolesRepository userRolesRepository, RolesRepository rolesRepository){
        this.usersRepository = usersRepository;
        this.userRolesRepository = userRolesRepository;
        this.rolesRepository = rolesRepository;
    }

    public ScbResponse addUserToRole(int userId, int roleId){
        try {
            if(userRolesRepository.findByUserIdAndRoleId(userId,roleId).isEmpty()) {
                Optional<UsersModel> user = usersRepository.findById(userId);
                Optional<RolesModel> role = rolesRepository.findById(roleId);
                if (user.isEmpty()) {
                    return ScbResponse.createSuccessResponse("User - " + Constants.NO_ENTITY_FOUND_WITH_ID + userId);
                } else if (role.isEmpty()) {
                    return ScbResponse.createSuccessResponse("Role - " + Constants.NO_ENTITY_FOUND_WITH_ID + userId);
                } else {
                    UserRolesKey userRolesKey = UserRolesKey.builder().roleId(roleId).userId(userId).build();
                    UserRolesModel userRolesModel = UserRolesModel.builder()
                            .role(role.get())
                            .user(user.get())
                            .id(userRolesKey).build();
                    userRolesRepository.save(userRolesModel);
                    return ScbResponse.createSuccessResponse("Success");
                }
            }else{
                return ScbResponse.createSuccessResponse("User Role relationship already exists for User ID: " + userId + " and Role ID: " + roleId);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse deleteUserFromRole(UserRolesKey userRolesKey){
        try {
            Optional<UserRolesModel> userRoles = userRolesRepository.findByUserIdAndRoleId(userRolesKey.getUserId(),userRolesKey.getRoleId());
            if(userRoles.isEmpty()) {
                return ScbResponse.createSuccessResponse("User Role relationship does not exist for User ID: " + userRolesKey.getUserId() + " and Role ID: " + userRolesKey.getRoleId());
            }else{
                userRolesRepository.delete(userRoles.get());
                return ScbResponse.createSuccessResponse("Success");
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
