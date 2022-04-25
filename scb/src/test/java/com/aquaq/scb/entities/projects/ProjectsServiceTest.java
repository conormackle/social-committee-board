package com.aquaq.scb.entities.projects;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.response.ResponseCodes;
import com.aquaq.scb.response.ScbResponse;
import com.aquaq.scb.utils.Constants;
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
class ProjectsServiceTest {

    @InjectMocks
    private ProjectsService projectsService;

    @Mock
    private ProjectsRepository projectsRepository;

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
    void test_getAll_returns_listOfUsers() {
        List<ProjectsModel> models = getModels();
        Mockito.when(projectsRepository.findAll()).thenReturn(models);
        actualResponse = projectsService.getAll();
        expectedResponse.setResponse(models);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getAll_returns_errorResponse_when_throwsException() {
        Mockito.when(projectsRepository.findAll()).thenThrow(new RuntimeException());
        actualResponse = projectsService.getAll();
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_getById_returns_model() {
        ProjectsModel model = getModel(id);
        Optional<ProjectsModel> modelOpt = Optional.of(model);
        Mockito.when(projectsRepository.findById(id)).thenReturn(modelOpt);
        actualResponse = projectsService.getById(id);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(projectsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = projectsService.getById(id);
        expectedResponse.setResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(projectsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = projectsService.getById(id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_create_returns_model() {
        ProjectsModel model = getModel(id);
        Mockito.when(projectsRepository.save(model)).thenReturn(model);
        actualResponse = projectsService.create(model);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_create_returns_errorResponse_when_throwsException() {
        ProjectsModel model = getModel(id);
        Mockito.when(projectsRepository.save(model)).thenThrow(new RuntimeException());
        actualResponse = projectsService.create(model);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_update_returns_model() {
        Optional<ProjectsModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        ProjectsModel modelRequestToChangeDetails = ProjectsModel.builder().details("testName2").id(id).build();
        Mockito.when(projectsRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(projectsRepository.save(any())).thenReturn(modelRequestToChangeDetails);
        actualResponse = projectsService.update(modelRequestToChangeDetails, id);
        expectedResponse.setResponse(modelRequestToChangeDetails);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_update_returns_noEntityFound() {
        ProjectsModel modelRequestToChangeName = ProjectsModel.builder().details("details updated").id(id).build();
        Mockito.when(projectsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = projectsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_update_returns_errorResponse_when_findById_throwsException() {
        ProjectsModel modelRequestToChangeName = ProjectsModel.builder().details("details updated").id(id).build();
        Mockito.when(projectsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = projectsService.update(modelRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_update_returns_errorResponse_when_save_throwsException() {
        Optional<ProjectsModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        ProjectsModel userRequestToChangeName = ProjectsModel.builder().details("details updated").id(id).build();
        Mockito.when(projectsRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(projectsRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = projectsService.update(userRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }


    ProjectsModel getModel(int id){
        return ProjectsModel.builder()
                .details("details")
                .id(id).build();
    }

    List<ProjectsModel> getModels(){
        ArrayList<ProjectsModel> models = new ArrayList<>();
        models.add(getModel(1));
        models.add(getModel(2));
        return models;
    }

}
