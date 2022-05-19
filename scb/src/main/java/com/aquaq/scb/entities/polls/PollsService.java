package com.aquaq.scb.entities.polls;

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
public class PollsService extends EntityServiceAbstract {

    private final PollsRepository pollsRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public PollsService(PollsRepository pollsRepository, ModelPropertyMapper modelPropertyMapper){
        this.pollsRepository = pollsRepository;
        this.modelPropertyMapper = modelPropertyMapper;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<PollsModel>> models = Optional.of(pollsRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<PollsModel> model = pollsRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(PollsModel model){
        try {
            if(model.getPollOptions() != null) {
                for (PollOptionsModel pollOption: model.getPollOptions()
                     ) {
                    pollOption.setPoll(model);
                }
            }
            return ScbResponse.createSuccessResponse(pollsRepository.save(model));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(PollsModel model, int id){
        try {
            PollsModel updatedModel;
            Optional<PollsModel> savedModel = pollsRepository.findById(id);
            if(savedModel.isPresent()){
                PollsModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = pollsRepository.save(updateModel);
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
        return pollsRepository.findByCreatedDateTimeBefore(endDateTime, page);
    }

    @Override
    public Page<Object> findByCreatedDateTime(LocalDateTime endDateTime, Pageable page) {
        return pollsRepository.findByCreatedDateTime(endDateTime, page);
    }

    @Override
    public Page<Object> findByCreatedDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page) {
        return pollsRepository.findByCreatedDateTimeBetween(startDateTime, endDateTime, page);
    }

}
