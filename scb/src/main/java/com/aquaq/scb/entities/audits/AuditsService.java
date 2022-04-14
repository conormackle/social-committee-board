package com.aquaq.scb.entities.audits;

import com.aquaq.scb.response.ScbResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.aquaq.scb.utils.GeneralUtils.copyProperties;

@Log4j2
@Service
public class AuditsService {


    private final AuditsRepository auditsRepository;

    @Autowired
    public AuditsService (AuditsRepository auditsRepository){
        this.auditsRepository = auditsRepository;
    }

    public ScbResponse getAll(){
        try{
            Optional<List<AuditModel>> auditModels = Optional.of(auditsRepository.findAll());
            return ScbResponse.createSuccessResponse(auditModels.get());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getById(int id){
        try{
            Optional<AuditModel> auditModel = auditsRepository.findById(id);
            if(auditModel.isPresent()){
                return ScbResponse.createSuccessResponse(auditModel);
            }else{
                return ScbResponse.createSuccessResponse(String.format("No entity found with ID: %s", id));
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse create(AuditModel auditModel){
        try {
            return ScbResponse.createSuccessResponse(auditsRepository.save(auditModel));
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }


    public ScbResponse update(AuditModel auditModel, int id){
        try {
            AuditModel updatedAuditModel;
            Optional<AuditModel> savedAuditModel = auditsRepository.findById(id);
            if(savedAuditModel.isPresent()){
                AuditModel updateAudit = savedAuditModel.get();
                copyProperties(auditModel, updateAudit);
                updatedAuditModel = auditsRepository.save(updateAudit);
                return ScbResponse.createSuccessResponse(updatedAuditModel);
            }else{
                return ScbResponse.createSuccessResponse("No entity found with ID: " + id);
            }
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

}
