package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.events.EventsModel;
import com.aquaq.scb.entities.events.EventsRepository;
import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Log4j2
@Service
public class PollsService {

    private final PollsRepository pollsRepository;

    @Autowired
    public PollsService(PollsRepository pollsRepository){
        this.pollsRepository = pollsRepository;
    }

    public ScbResponse getByPollId(Integer pollId){
        try{
            Optional<PollsModel> pollsModel = pollsRepository.findById(pollId);
            if(pollsModel.isPresent()){
                return ScbResponse.createSuccessResponse(pollsModel);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No poll found for ID: %s", pollId));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }
}
