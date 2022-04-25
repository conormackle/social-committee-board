package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.events.images.EventImagesModel;
import com.aquaq.scb.entities.events.images.EventImagesRepository;
import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ScbResponse;
import com.aquaq.scb.utils.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EventsService {

    private final EventsRepository eventsRepository;
    private final EventImagesRepository eventImagesRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public EventsService(EventsRepository eventsRepository, EventImagesRepository eventImagesRepository, ModelPropertyMapper modelPropertyMapper){
        this.eventsRepository = eventsRepository;
        this.eventImagesRepository = eventImagesRepository;
        this.modelPropertyMapper  = modelPropertyMapper;
    }

    public ScbResponse getById(Integer id){
        try{
            Optional<EventsModel> model = eventsRepository.findById(id);
            if(model.isPresent()){
               return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
            }else{
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getAll(){
        try{
            List<EventsModel> eventsModels = eventsRepository.findAll();
            if(!eventsModels.isEmpty()){
                return ScbResponse.createSuccessResponse(eventsModels);
            }else{
                return ScbResponse.createSuccessResponse("No entities found");
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(EventsModel model){
        try {
            return ScbResponse.createSuccessResponse(eventsRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(EventsModel model, int id){
        try {
            EventsModel updatedModel;
            Optional<EventsModel> savedModel = eventsRepository.findById(id);
            if(savedModel.isPresent()){
                EventsModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = eventsRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse addImage(EventImagesModel model, int id){
        try {
            Optional<EventsModel> event = eventsRepository.findById(id);
            if(event.isPresent()){
                model.setEvent(event.get());
                eventImagesRepository.save(model);
                return ScbResponse.createSuccessResponse("Success");
            }else{
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse updateImage(EventImagesModel model){
        try {
            EventImagesModel updatedModel;
            Optional<EventImagesModel> savedModel = eventImagesRepository.findById(model.getId());
            if(savedModel.isPresent()){
                EventImagesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = eventImagesRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + model.getId());
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getThumbnail(int id){
        try {
            Optional<EventImagesModel> eventImageThumbnail = eventImagesRepository.findFirstByEventIdAndThumbnailTrue(id);
            return eventImageThumbnail.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
