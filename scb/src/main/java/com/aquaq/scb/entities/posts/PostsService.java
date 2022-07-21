package com.aquaq.scb.entities.posts;

import com.aquaq.scb.entities.EntityServiceAbstract;
import com.aquaq.scb.entities.events.images.EventImagesModel;
import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.posts.images.PostsImagesModel;
import com.aquaq.scb.entities.posts.images.PostsImagesRepository;
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
public class PostsService extends EntityServiceAbstract {

    private final PostsRepository postsRepository;

    private final PostsImagesRepository postsImagesRepository;
    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public PostsService(PostsRepository postsRepository, PostsImagesRepository postsImagesRepository, ModelPropertyMapper modelPropertyMapper) {
        this.postsRepository = postsRepository;
        this.postsImagesRepository = postsImagesRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getThumbnail(int id) {
        try {
            Optional<PostsImagesModel> thumbnail = postsImagesRepository.findFirstByPostIdAndThumbnailTrue(id);
            return thumbnail.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse addImage(PostsImagesModel model, int id) {
        try {
            Optional<PostsModel> posts = postsRepository.findById(id);
            if (posts.isPresent()) {
                posts.get().setUpdatedDateTime(LocalDateTime.now());
                model.setPost(posts.get());
                postsImagesRepository.save(model);
                return ScbResponse.createSuccessResponse("Success");
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse updateImage(PostsImagesModel model) {
        try {
            PostsImagesModel updatedModel;
            Optional<PostsImagesModel> savedModel = postsImagesRepository.findById(model.getId());
            if (savedModel.isPresent()) {
                PostsImagesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = postsImagesRepository.save(updateModel);
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
            Optional<List<PostsModel>> models = Optional.of(postsRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get().stream().filter(x -> !x.isDeleted()).collect(Collectors.toList()));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<PostsModel> model = postsRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(PostsModel model){
        try {
            return ScbResponse.createSuccessResponse(postsRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(PostsModel model, int id){
        try {

            PostsModel updatedModel;
            Optional<PostsModel> savedModel = postsRepository.findById(id);
            if(savedModel.isPresent()){
                PostsModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = postsRepository.save(updateModel);
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
        return postsRepository.findByCreatedDateTimeBefore(endDateTime, page);
    }

    @Override
    public Page<Object> findByCreatedDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page) {
        return postsRepository.findByCreatedDateTimeBetween(startDateTime, endDateTime, page);
    }

    public ScbResponse delete(int id) {
        try {
            Optional<PostsModel> modelToDelete = postsRepository.findById(id);
            if (modelToDelete.isPresent()) {
                modelToDelete.get().setDeleted(true);
                postsRepository.save(modelToDelete.get());
                return ScbResponse.createSuccessResponse("Success");
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
