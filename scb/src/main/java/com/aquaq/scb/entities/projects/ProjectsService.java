package com.aquaq.scb.entities.projects;

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

    public ScbResponse getById(Integer id){
        try{
            Optional<ProjectsModel> projectsModel = projectsRepository.findById(id);
            if(projectsModel.isPresent()){
                return ScbResponse.createSuccessResponse(projectsModel);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No project found for ID: %s", id));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getAll(){
        try{
            List<ProjectsModel> projectsModels = projectsRepository.findAll();
            if(!projectsModels.isEmpty()){
                return ScbResponse.createSuccessResponse(projectsModels);
            }else{
                return ScbResponse.createSuccessResponse("No projects found");
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
