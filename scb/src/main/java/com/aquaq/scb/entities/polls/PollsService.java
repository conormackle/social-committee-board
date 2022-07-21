package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.EntityServiceAbstract;
import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.polls.images.PollsImagesModel;
import com.aquaq.scb.entities.polls.images.PollsImagesRepository;
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
public class PollsService extends EntityServiceAbstract {

    private final PollsRepository pollsRepository;

    private final PollsImagesRepository pollsImagesRepository;
    private final PollOptionRepository pollOptionRepository;

    private final ModelPropertyMapper modelPropertyMapper;

    @Autowired
    public PollsService(PollsRepository pollsRepository, PollsImagesRepository pollsImagesRepository, PollOptionRepository pollOptionRepository, ModelPropertyMapper modelPropertyMapper) {
        this.pollsRepository = pollsRepository;
        this.pollsImagesRepository = pollsImagesRepository;
        this.modelPropertyMapper = modelPropertyMapper;
        this.pollOptionRepository = pollOptionRepository;
    }

    public ScbResponse getThumbnail(int id) {
        try {
            Optional<PollsImagesModel> thumbnail = pollsImagesRepository.findFirstByPollIdAndThumbnailTrue(id);
            return thumbnail.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }


    public ScbResponse addImage(PollsImagesModel model, int id) {
        try {
            Optional<PollsModel> polls = pollsRepository.findById(id);
            if (polls.isPresent()) {
                polls.get().setUpdatedDateTime(LocalDateTime.now());
                model.setPoll(polls.get());
                pollsImagesRepository.save(model);
                return ScbResponse.createSuccessResponse("Success");
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse updateImage(PollsImagesModel model) {
        try {
            PollsImagesModel updatedModel;
            Optional<PollsImagesModel> savedModel = pollsImagesRepository.findById(model.getId());
            if (savedModel.isPresent()) {
                PollsImagesModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = pollsImagesRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + model.getId());
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getAll() {
        try {
            Optional<List<PollsModel>> models = Optional.of(pollsRepository.findAll());
            return ScbResponse.createSuccessResponse(models.get().stream().filter(x -> !x.isDeleted()).collect(Collectors.toList()));
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id) {
        try {
            Optional<PollsModel> model = pollsRepository.findById(id);
            return model.map(ScbResponse::createSuccessResponse).orElseGet(() -> ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id));
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(PollsModel model) {
        try {
            if (model.getPollOptions() != null) {
                for (PollOptionsModel pollOption : model.getPollOptions()
                ) {
                    pollOption.setPoll(model);
                }
            }
            return ScbResponse.createSuccessResponse(pollsRepository.save(model));
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse update(PollsModel model, int id) {
        try {
            PollsModel updatedModel;
            Optional<PollsModel> savedModel = pollsRepository.findById(id);
            if (savedModel.isPresent()) {
                PollsModel updateModel = savedModel.get();
                modelPropertyMapper.copyModelProperties(model, updateModel);
                updatedModel = pollsRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    @Override
    public Page<Object> findByCreatedDateTimeBefore(LocalDateTime endDateTime, Pageable page) {
        return pollsRepository.findByCreatedDateTimeBefore(endDateTime, page);
    }

    @Override
    public Page<Object> findByCreatedDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, Pageable page) {
        return pollsRepository.findByCreatedDateTimeBetween(startDateTime, endDateTime, page);
    }

    public ScbResponse addVote(PollOptionVoteModel pollOptionVoteModel) {
        try {
            PollOptionsModel updatedModel;
            Optional<PollOptionsModel> savedModel = pollOptionRepository.findById(pollOptionVoteModel.id.getPollOption().getId());
            if (savedModel.isPresent()) {
                PollOptionsModel updateModel = savedModel.get();
                updateModel.addVote(pollOptionVoteModel);
                updatedModel = pollOptionRepository.save(updateModel);
                return ScbResponse.createSuccessResponse(updatedModel);
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + pollOptionVoteModel.id.getPollOption().getId());
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse deleteVote(PollOptionVoteModel pollOptionVoteModel) {
        try {
            PollOptionsModel updatedModel;
            Optional<PollOptionsModel> savedModel = pollOptionRepository.findById(pollOptionVoteModel.id.getPollOption().getId());
            if (savedModel.isPresent()) {
                PollOptionsModel updateModel = savedModel.get();
                boolean success = updateModel.deleteVote(pollOptionVoteModel);
                updatedModel = pollOptionRepository.save(updateModel);
                if (success) {
                    return ScbResponse.createSuccessResponse(updatedModel);
                } else {
                    return ScbResponse.createNotFoundResponse(Constants.NO_ENTITY_FOUND_WITH_ID + pollOptionVoteModel.getId().getPollOption().getId() + " user ID: " + pollOptionVoteModel.getId().getUser().getId());
                }
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + pollOptionVoteModel.id.getPollOption().getId());
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse delete(int id) {
        try {
            Optional<PollsModel> modelToDelete = pollsRepository.findById(id);
            if (modelToDelete.isPresent()) {
                modelToDelete.get().setDeleted(true);
                pollsRepository.save(modelToDelete.get());
                return ScbResponse.createSuccessResponse("Success");
            } else {
                return ScbResponse.createSuccessResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
            }
        } catch (Exception e) {
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
