package com.aquaq.scb.entities.posts;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.polls.PollsModel;
import com.aquaq.scb.entities.polls.PollsRepository;
import com.aquaq.scb.entities.polls.PollsService;
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
class PostsServiceTest {

    @InjectMocks
    private PostsService postsService;

    @Mock
    private PostsRepository postsRepository;

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
        List<PostsModel> models = getModels();
        Mockito.when(postsRepository.findAll()).thenReturn(models);
        actualResponse = postsService.getAll();
        expectedResponse.setResponse(models);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getAll_returns_errorResponse_when_throwsException() {
        Mockito.when(postsRepository.findAll()).thenThrow(new RuntimeException());
        actualResponse = postsService.getAll();
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_getById_returns_model() {
        PostsModel model = getModel(id);
        Optional<PostsModel> modelOpt = Optional.of(model);
        Mockito.when(postsRepository.findById(id)).thenReturn(modelOpt);
        actualResponse = postsService.getById(id);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(postsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = postsService.getById(id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(postsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = postsService.getById(id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_createPost_returns_post() {
        PostsModel model = getModel(id);
        Mockito.when(postsRepository.save(model)).thenReturn(model);
        actualResponse = postsService.create(model);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_createPost_returns_errorResponse_when_throwsException() {
        PostsModel model = getModel(id);
        Mockito.when(postsRepository.save(model)).thenThrow(new RuntimeException());
        actualResponse = postsService.create(model);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updatePost_returns_post() {
        Optional<PostsModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        PostsModel modelRequestToChangeName = PostsModel.builder().content("testName2").id(id).build();
        Mockito.when(postsRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(postsRepository.save(any())).thenReturn(modelRequestToChangeName);
        actualResponse = postsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(modelRequestToChangeName);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updatePost_returns_noEntityFound() {
        PostsModel modelRequestToChangeName = PostsModel.builder().content("testName2").id(id).build();
        Mockito.when(postsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = postsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse("No entity found with ID: " + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updatePost_returns_errorResponse_when_findById_throwsException() {
        PostsModel modelRequestToChangeName = PostsModel.builder().content("testName2").id(id).build();
        Mockito.when(postsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = postsService.update(modelRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updatePost_returns_errorResponse_when_save_throwsException() {
        Optional<PostsModel> userBeforeUpdateOpt = Optional.of(getModel(id));
        PostsModel userRequestToChangeName = PostsModel.builder().content("testName2").id(id).build();
        Mockito.when(postsRepository.findById(id)).thenReturn(userBeforeUpdateOpt);
        Mockito.when(postsRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = postsService.update(userRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }


    PostsModel getModel(int id){
        return PostsModel.builder()
                .content("content")
                .id(id)
                .build();
    }


    List<PostsModel> getModels(){
        ArrayList<PostsModel> modelList = new ArrayList<>();
        modelList.add(getModel(1));
        modelList.add(getModel(2));
        return modelList;
    }

}
