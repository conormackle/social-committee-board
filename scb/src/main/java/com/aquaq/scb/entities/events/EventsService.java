package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.polls.PollsModel;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class EventsService {

    private final EventsRepository eventsRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public EventsService(EventsRepository eventsRepository, ModelPropertyMapper modelPropertyMapper){
        this.eventsRepository = eventsRepository;
        this.modelPropertyMapper  = modelPropertyMapper;
    }

    public ScbResponse getById(Integer id){
        try{
            Optional<EventsModel> model = eventsRepository.findById(id);
            if(model.isPresent()){
               return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id)));
            }else{
                return ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id));
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
                return ScbResponse.createSuccessResponse("No entity found with ID: " + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
