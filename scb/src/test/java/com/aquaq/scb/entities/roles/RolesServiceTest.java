package com.aquaq.scb.entities.roles;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.projects.ProjectsModel;
import com.aquaq.scb.entities.projects.ProjectsRepository;
import com.aquaq.scb.entities.projects.ProjectsService;
import com.aquaq.scb.entities.users.UsersModel;
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
public class RolesServiceTest {

    @InjectMocks
    private RolesService rolesService;

    @Mock
    private RolesRepository rolesRepository;

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

    RolesModel getModel(int id){
        return RolesModel.builder()
                .name("details")
                .id(id).build();
    }

    List<RolesModel> getModels(){
        ArrayList<RolesModel> models = new ArrayList<>();
        models.add(getModel(1));
        models.add(getModel(2));
        return models;
    }

    @Test
    void test_getAll_returns_listOfRoles() {
        List<RolesModel> models = getModels();
        Mockito.when(rolesRepository.findAll()).thenReturn(models);
        actualResponse = rolesService.getAll();
        expectedResponse.setResponse(models);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getAll_returns_errorResponse_when_throwsException() {
        Mockito.when(rolesRepository.findAll()).thenThrow(new RuntimeException());
        actualResponse = rolesService.getAll();
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_getById_returns_model() {
        RolesModel model = getModel(id);
        Optional<RolesModel> modelOpt = Optional.of(model);
        Mockito.when(rolesRepository.findById(id)).thenReturn(modelOpt);
        actualResponse = rolesService.getById(id);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(rolesRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = rolesService.getById(id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(rolesRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = rolesService.getById(id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_create_returns_model() {
        RolesModel model = getModel(id);
        Mockito.when(rolesRepository.save(model)).thenReturn(model);
        actualResponse = rolesService.create(model);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_create_returns_errorResponse_when_throwsException() {
        RolesModel model = getModel(id);
        Mockito.when(rolesRepository.save(model)).thenThrow(new RuntimeException());
        actualResponse = rolesService.create(model);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_update_returns_model() {
        Optional<RolesModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        RolesModel modelRequestToChangeName = RolesModel.builder().name("testName2").id(id).build();
        Mockito.when(rolesRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(rolesRepository.save(any())).thenReturn(modelRequestToChangeName);
        actualResponse = rolesService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(modelRequestToChangeName);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_update_returns_noEntityFound() {
        RolesModel modelRequestToChangeName = RolesModel.builder().name("testName2").id(id).build();
        Mockito.when(rolesRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = rolesService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_update_returns_errorResponse_when_findById_throwsException() {
        RolesModel modelRequestToChangeName = RolesModel.builder().name("testName2").id(id).build();
        Mockito.when(rolesRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = rolesService.update(modelRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_update_returns_errorResponse_when_save_throwsException() {
        Optional<RolesModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        RolesModel userRequestToChangeName = RolesModel.builder().name("testName2").id(id).build();
        Mockito.when(rolesRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(rolesRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = rolesService.update(userRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }
}
