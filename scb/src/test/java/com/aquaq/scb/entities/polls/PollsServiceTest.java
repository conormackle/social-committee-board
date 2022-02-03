package com.aquaq.scb.entities.polls;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ResponseCodes;
import com.aquaq.scb.response.ScbResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.aquaq.scb.helper.TestHelper.testExceptionScbResponse;
import static com.aquaq.scb.helper.TestHelper.testSuccessScbResponse;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PollsServiceTest {

    @InjectMocks
    private PollsService pollsService;

    @Mock
    private PollsRepository pollsRepository;

    @Mock
    private ModelPropertyMapper modelPropertyMapper;

    private ScbResponse expectedResponse;
    private ScbResponse actualResponse;
    private final int id = 5;

    @BeforeEach
    private void initAll(){
        expectedResponse = new ScbResponse();
        actualResponse = new ScbResponse();
    }

    @Test
    void test_getAll_returns_listOfModels() {
        List<PollsModel> models = getModels();
        Mockito.when(pollsRepository.findAll()).thenReturn(models);
        actualResponse = pollsService.getAll();
        expectedResponse.setResponse(models);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getAll_returns_errorResponse_when_throwsException() {
        Mockito.when(pollsRepository.findAll()).thenThrow(new RuntimeException());
        actualResponse = pollsService.getAll();
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_getById_returns_model() {
        PollsModel model = getModel(id);
        Optional<PollsModel> modelOpt = Optional.of(model);
        Mockito.when(pollsRepository.findById(id)).thenReturn(modelOpt);
        actualResponse = pollsService.getById(id);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(pollsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = pollsService.getById(id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(pollsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = pollsService.getById(id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_createUser_returns_user() {
        PollsModel model = getModel(id);
        Mockito.when(pollsRepository.save(model)).thenReturn(model);
        actualResponse = pollsService.create(model);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_createUser_returns_errorResponse_when_throwsException() {
        PollsModel model = getModel(id);
        Mockito.when(pollsRepository.save(model)).thenThrow(new RuntimeException());
        actualResponse = pollsService.create(model);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updateUser_returns_user() {
        Optional<PollsModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        PollsModel modelRequestToChangeName = PollsModel.builder().name("testName2").id(id).build();
        Mockito.when(pollsRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(pollsRepository.save(any())).thenReturn(modelRequestToChangeName);
        actualResponse = pollsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(modelRequestToChangeName);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updateUser_returns_noEntityFound() {
        PollsModel modelRequestToChangeName = PollsModel.builder().name("testName2").id(id).build();
        Mockito.when(pollsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = pollsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updateUser_returns_errorResponse_when_findById_throwsException() {
        PollsModel modelRequestToChangeName = PollsModel.builder().name("testName2").id(id).build();
        Mockito.when(pollsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = pollsService.update(modelRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updateUser_returns_errorResponse_when_save_throwsException() {
        Optional<PollsModel> userBeforeUpdateOpt = Optional.of(getModel(id));
        PollsModel userRequestToChangeName = PollsModel.builder().name("testName2").id(id).build();
        Mockito.when(pollsRepository.findById(id)).thenReturn(userBeforeUpdateOpt);
        Mockito.when(pollsRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = pollsService.update(userRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }


    PollsModel getModel(int id){
        return PollsModel.builder()
                .details("details")
                .id(id)
                .name("testName").build();
    }


    List<PollsModel> getModels(){
        ArrayList<PollsModel> modelList = new ArrayList<>();
        modelList.add(getModel(1));
        modelList.add(getModel(2));
        return modelList;
    }

}
