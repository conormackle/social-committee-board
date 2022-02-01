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
import static com.aquaq.scb.helper.TestHelper.testSuccessScbResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    private ScbResponse expectedResponse;
    private ScbResponse actualResponse;

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


    UsersModel getUserModel(int id){
        return UsersModel.builder()
                .email("test@test.com")
                .emailVerified(1)
                .id(id)
                .name("testName").build();
    }

    List<UsersModel> getUsersModels(){
        ArrayList<UsersModel> usersModels = new ArrayList<>();
        usersModels.add(getUserModel(1));
        usersModels.add(getUserModel(2));
        return usersModels;
    }

}
