package com.aquaq.scb.entities.users.projects;

import com.aquaq.scb.entities.projects.ProjectsModel;
import com.aquaq.scb.entities.projects.ProjectsRepository;
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
public class UserProjectsService {

    private final UsersRepository usersRepository;
    private final UserProjectsRepository userProjectsRepository;
    private final ProjectsRepository projectsRepository;

    @Autowired
    public UserProjectsService(UsersRepository usersRepository, UserProjectsRepository userProjectsRepository, ProjectsRepository projectsRepository){
        this.usersRepository = usersRepository;
        this.userProjectsRepository = userProjectsRepository;
        this.projectsRepository = projectsRepository;
    }

    public ScbResponse addUserToProject(int userId, int projectId){
        try {
            if(userProjectsRepository.findByUserIdAndProjectId(userId,projectId).isEmpty()) {
                Optional<UsersModel> user = usersRepository.findById(userId);
                Optional<ProjectsModel> project = projectsRepository.findById(projectId);
                if (user.isEmpty()) {
                    return ScbResponse.createSuccessResponse("User - " + Constants.NO_ENTITY_FOUND_WITH_ID + userId);
                } else if (project.isEmpty()) {
                    return ScbResponse.createSuccessResponse("Project - " + Constants.NO_ENTITY_FOUND_WITH_ID + userId);
                } else {
                    UserProjectsKey userProjectsKey = UserProjectsKey.builder().projectId(projectId).userId(userId).build();
                    UserProjectsModel userProjectsModel = UserProjectsModel.builder()
                            .id(userProjectsKey)
                            .project(project.get())
                            .user(user.get()).build();
                    userProjectsRepository.save(userProjectsModel);
                    return ScbResponse.createSuccessResponse("Success");
                }
            }else{
                return ScbResponse.createSuccessResponse("User Project relationship already exists for User ID: " + userId + " and Project ID: " + projectId);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse deleteUserFromProject(int userId, int projectId){
        try {
            Optional<UserProjectsModel> userProjectsModel = userProjectsRepository.findByUserIdAndProjectId(userId,projectId);
            if(userProjectsModel.isEmpty()) {
                return ScbResponse.createSuccessResponse("User Project relationship does not exist for User ID: " + userId + " and Project ID: " + projectId);
            }else{
                userProjectsRepository.delete(userProjectsModel.get());
                return ScbResponse.createSuccessResponse("Success");
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
