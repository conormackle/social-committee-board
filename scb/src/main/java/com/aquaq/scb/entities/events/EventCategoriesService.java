package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EventCategoriesService {

    private final EventCategoriesRepository eventCategoriesRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public EventCategoriesService(EventCategoriesRepository eventCategoriesRepository, ModelPropertyMapper modelPropertyMapper) {
        this.eventCategoriesRepository = eventCategoriesRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<EventCategoriesModel>> models = Optional.of(eventCategoriesRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<EventCategoriesModel> model = eventCategoriesRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id)));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(EventCategoriesModel model){
        try {
            return ScbResponse.createSuccessResponse(eventCategoriesRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(EventCategoriesModel model, int id){
        try {

            EventCategoriesModel updatedModel;
            Optional<EventCategoriesModel> savedModel = eventCategoriesRepository.findById(id);
            if(savedModel.isPresent()){
                EventCategoriesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = eventCategoriesRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            }else{
                return ScbResponse.createSuccessResponse("No entity found with ID: " + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
