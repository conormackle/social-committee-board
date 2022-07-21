package com.aquaq.scb.entities.projects;

import com.aquaq.scb.entities.EntityServiceAbstract;
import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.posts.PostsModel;
import com.aquaq.scb.entities.posts.images.PostsImagesModel;
import com.aquaq.scb.entities.projects.images.ProjectsImagesModel;
import com.aquaq.scb.entities.projects.images.ProjectsImagesRepository;
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
import java.util.stream.Collectors;

@Log4j2
@Service
public class ProjectsService extends EntityServiceAbstract {

    private final ProjectsRepository projectsRepository;

    private final ProjectsImagesRepository projectsImagesRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public ProjectsService(ProjectsRepository projectsRepository, ProjectsImagesRepository projectsImagesRepository, ModelPropertyMapper modelPropertyMapper){
        this.projectsRepository = projectsRepository;
        this.projectsImagesRepository = projectsImagesRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getThumbnail(int id) {
        try {
            Optional<ProjectsImagesModel> thumbnail = projectsImagesRepository.findFirstByProjectIdAndThumbnailTrue(id);
            return thumbnail.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse addImage(ProjectsImagesModel childModel, int id) {
        try {
            Optional<ProjectsModel> parentModel = projectsRepository.findById(id);
            if (parentModel.isPresent()) {
                parentModel.get().setUpdatedDateTime(LocalDateTime.now());
                childModel.setProject(parentModel.get());
                projectsImagesRepository.save(childModel);
                return ScbResponse.createSuccessResponse("Success");
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse updateImage(ProjectsImagesModel model) {
        try {
            ProjectsImagesModel updatedModel;
            Optional<ProjectsImagesModel> savedModel = projectsImagesRepository.findById(model.getId());
            if (savedModel.isPresent()) {
                ProjectsImagesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = projectsImagesRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + model.getId());
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getAll(){
        try{
            Optional<List<ProjectsModel>> models = Optional.of(projectsRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get().stream().filter(x -> !x.isDeleted()).collect(Collectors.toList()));
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
    public Page<Object> findByCreatedDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page) {
        return projectsRepository.findByCreatedDateTimeBetween(startDateTime, endDateTime, page);
    }

    public ScbResponse delete(int id) {
        try {
            Optional<ProjectsModel> modelToDelete = projectsRepository.findById(id);
            if (modelToDelete.isPresent()) {
                modelToDelete.get().setDeleted(true);
                projectsRepository.save(modelToDelete.get());
                return ScbResponse.createSuccessResponse("Success");
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
