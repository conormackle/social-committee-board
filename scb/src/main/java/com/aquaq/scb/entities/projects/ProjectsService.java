package com.aquaq.scb.entities.projects;

import com.aquaq.scb.entities.EntityServiceAbstract;
import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ScbResponse;
import com.aquaq.scb.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ProjectsService extends EntityServiceAbstract {

    private final ProjectsRepository projectsRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository, ModelPropertyMapper modelPropertyMapper){
        this.projectsRepository = projectsRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<ProjectsModel>> models = Optional.of(projectsRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<ProjectsModel> model = projectsRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(ProjectsModel model){
        try {
            return ScbResponse.createSuccessResponse(projectsRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(ProjectsModel model, int id){
        try {

            ProjectsModel updatedModel;
            Optional<ProjectsModel> savedModel = projectsRepository.findById(id);
            if(savedModel.isPresent()){
                ProjectsModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = projectsRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    @Override
    public Page<Object> findByCreatedDateTimeBefore(LocalDateTime endDateTime, Pageable page) {
        return projectsRepository.findByCreatedDateTimeBefore(endDateTime, page);
    }

    @Override
    public Page<Object> findByCreatedDateTime(LocalDateTime endDateTime, Pageable page) {
        return projectsRepository.findByCreatedDateTime(endDateTime, page);
    }

    @Override
    public Page<Object> findByCreatedDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page) {
        return projectsRepository.findByCreatedDateTimeBetween(startDateTime, endDateTime, page);
    }
}
