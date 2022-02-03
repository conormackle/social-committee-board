package com.aquaq.scb.entities.projects;


import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.entities.users.UsersRepository;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProjectsService {

    private final ProjectsRepository projectsRepository;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository){
        this.projectsRepository = projectsRepository;
    }

    public ScbResponse getByProjectId(Integer projectId){
        try{
            Optional<ProjectsModel> projectsModel = projectsRepository.findById(projectId);
            if(projectsModel.isPresent()){
                return ScbResponse.createSuccessResponse(projectsModel);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No project found for ID: %s", projectId));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getProjects(){
        try{
            List<ProjectsModel> projectsModels = projectsRepository.findAll();
            if(!projectsModels.isEmpty()){
                return ScbResponse.createSuccessResponse(projectsModels);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No projects found"));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}