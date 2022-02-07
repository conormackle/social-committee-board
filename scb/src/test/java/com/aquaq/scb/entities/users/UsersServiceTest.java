package com.aquaq.scb.entities.users;

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
class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    private ScbResponse expectedResponse;
    private ScbResponse actualResponse;
    private final int id = 5;

    @Mock
    private ModelPropertyMapper modelPropertyMapper;

    @BeforeEach
    private void initAll(){
        expectedResponse = new ScbResponse();
        actualResponse = new ScbResponse();
    }

    @Test
    void test_getAll_returns_listOfUsers() {
        List<UsersModel> models = getModels();
        Mockito.when(usersRepository.findAll()).thenReturn(models);
        actualResponse = usersService.getAll();
        expectedResponse.setResponse(models);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getAll_returns_errorResponse_when_throwsException() {
        Mockito.when(usersRepository.findAll()).thenThrow(new RuntimeException());
        actualResponse = usersService.getAll();
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_getById_returns_model() {
        UsersModel model = getModel(id);
        Optional<UsersModel> modelOpt = Optional.of(model);
        Mockito.when(usersRepository.findById(id)).thenReturn(modelOpt);
        actualResponse = usersService.getById(id);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(usersRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = usersService.getById(id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(usersRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = usersService.getById(id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_create_returns_model() {
        UsersModel model = getModel(id);
        Mockito.when(usersRepository.save(model)).thenReturn(model);
        actualResponse = usersService.create(model);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_create_returns_errorResponse_when_throwsException() {
        UsersModel model = getModel(id);
        Mockito.when(usersRepository.save(model)).thenThrow(new RuntimeException());
        actualResponse = usersService.create(model);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_update_returns_model() {
        Optional<UsersModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        UsersModel modelRequestToChangeName = UsersModel.builder().name("testName2").id(id).build();
        Mockito.when(usersRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(usersRepository.save(any())).thenReturn(modelRequestToChangeName);
        actualResponse = usersService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(modelRequestToChangeName);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_update_returns_noEntityFound() {
        UsersModel modelRequestToChangeName = UsersModel.builder().name("testName2").id(id).build();
        Mockito.when(usersRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = usersService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_update_returns_errorResponse_when_findById_throwsException() {
        UsersModel modelRequestToChangeName = UsersModel.builder().name("testName2").id(id).build();
        Mockito.when(usersRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = usersService.update(modelRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_update_returns_errorResponse_when_save_throwsException() {
        Optional<UsersModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        UsersModel userRequestToChangeName = UsersModel.builder().name("testName2").id(id).build();
        Mockito.when(usersRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(usersRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = usersService.update(userRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }


    UsersModel getModel(int id){
        return UsersModel.builder()
                .email("test@test.com")
                .emailVerified(1)
                .id(id)
                .name("testName1").build();
    }

    List<UsersModel> getModels(){
        ArrayList<UsersModel> models = new ArrayList<>();
        models.add(getModel(1));
        models.add(getModel(2));
        return models;
    }

}
