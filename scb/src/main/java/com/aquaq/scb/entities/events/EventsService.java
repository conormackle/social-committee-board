package com.aquaq.scb.entities.events;

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

    @Autowired
    public EventsService(EventsRepository eventsRepository){
        this.eventsRepository = eventsRepository;
    }

    public ScbResponse getByEventId(Integer eventId){
        try{
            Optional<EventsModel> eventsModels = eventsRepository.findById(eventId);
            if(eventsModels.isPresent()){
                return ScbResponse.createSuccessResponse(eventsModels);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No event found for ID: %s", eventId));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getEvents(){
        try{
            List<EventsModel> eventsModels = eventsRepository.findAll();
            if(!eventsModels.isEmpty()){
                return ScbResponse.createSuccessResponse(eventsModels);
            }else{
                return ScbResponse.createSuccessResponse("No events found");
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
