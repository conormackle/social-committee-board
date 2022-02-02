package com.aquaq.scb.entities.users;

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
    private final int userId = 5;

    @BeforeEach
    private void initAll(){
        expectedResponse = new ScbResponse();
        actualResponse = new ScbResponse();
    }

    @Test
    void test_getAll_returns_listOfUsers() {
        List<UsersModel> usersModels = getUsersModels();
        Mockito.when(usersRepository.findAll()).thenReturn(usersModels);
        actualResponse = usersService.getAll();
        expectedResponse.setResponse(usersModels);
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
    void test_getById_returns_user() {
        UsersModel user = getUserModel(userId);
        Optional<UsersModel> userOpt = Optional.of(user);
        Mockito.when(usersRepository.findById(userId)).thenReturn(userOpt);
        actualResponse = usersService.getById(userId);
        expectedResponse.setResponse(user);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(usersRepository.findById(userId)).thenReturn(Optional.empty());
        actualResponse = usersService.getById(userId);
        expectedResponse.setResponse("No entity found with ID: " + userId);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(usersRepository.findById(userId)).thenThrow(new RuntimeException());
        actualResponse = usersService.getById(userId);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_createUser_returns_user() {
        UsersModel user = getUserModel(userId);
        Mockito.when(usersRepository.save(user)).thenReturn(user);
        actualResponse = usersService.createUser(user);
        expectedResponse.setResponse(user);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_createUser_returns_errorResponse_when_throwsException() {
        UsersModel user = getUserModel(userId);
        Mockito.when(usersRepository.save(user)).thenThrow(new RuntimeException());
        actualResponse = usersService.createUser(user);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updateUser_returns_user() {
        Optional<UsersModel> userBeforeUpdateOpt = Optional.of(getUserModel(userId));
        UsersModel userRequestToChangeName = UsersModel.builder().name("testName2").id(userId).build();
        Mockito.when(usersRepository.findById(userId)).thenReturn(userBeforeUpdateOpt);
        Mockito.when(usersRepository.save(any())).thenReturn(userRequestToChangeName);
        actualResponse = usersService.update(userRequestToChangeName, userId);
        expectedResponse.setResponse(userRequestToChangeName);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updateUser_returns_noEntityFound() {
        UsersModel userRequestToChangeName = UsersModel.builder().name("testName2").id(userId).build();
        Mockito.when(usersRepository.findById(userId)).thenReturn(Optional.empty());
        actualResponse = usersService.update(userRequestToChangeName, userId);
        expectedResponse.setResponse("No entity found with ID: " + userId);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updateUser_returns_errorResponse_when_findById_throwsException() {
        UsersModel userRequestToChangeName = UsersModel.builder().name("testName2").id(userId).build();
        Mockito.when(usersRepository.findById(userId)).thenThrow(new RuntimeException());
        actualResponse = usersService.update(userRequestToChangeName, userId);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updateUser_returns_errorResponse_when_save_throwsException() {
        Optional<UsersModel> userBeforeUpdateOpt = Optional.of(getUserModel(userId));
        UsersModel userRequestToChangeName = UsersModel.builder().name("testName2").id(userId).build();
        Mockito.when(usersRepository.findById(userId)).thenReturn(userBeforeUpdateOpt);
        Mockito.when(usersRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = usersService.update(userRequestToChangeName, userId);
        testExceptionScbResponse(actualResponse);
    }


    UsersModel getUserModel(int id){
        return UsersModel.builder()
                .email("test@test.com")
                .emailVerified(1)
                .id(id)
                .name("testName1").build();
    }

    List<UsersModel> getUsersModels(){
        ArrayList<UsersModel> usersModels = new ArrayList<>();
        usersModels.add(getUserModel(1));
        usersModels.add(getUserModel(2));
        return usersModels;
    }

}
