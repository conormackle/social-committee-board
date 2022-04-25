package com.aquaq.scb.entities.events;

import com.aquaq.scb.entities.mapper.ModelPropertyMapper;
import com.aquaq.scb.entities.posts.PostsModel;
import com.aquaq.scb.entities.posts.PostsRepository;
import com.aquaq.scb.entities.posts.PostsService;
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

import java.time.LocalDateTime;
import java.time.LocalDateTime.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.aquaq.scb.helper.TestHelper.testExceptionScbResponse;
import static com.aquaq.scb.helper.TestHelper.testSuccessScbResponse;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventsServiceTest {

    @InjectMocks
    private EventsService eventsService;

    @Mock
    private EventsRepository eventsRepository;

    @Mock
    private ModelPropertyMapper modelPropertyMapper;

    private ScbResponse expectedResponse;
    private ScbResponse actualResponse;
    private final int id = 5;

    @BeforeEach
    private void initAll() {
        expectedResponse = new ScbResponse();
        actualResponse = new ScbResponse();
    }

    @Test
    void test_getAll_returns_listOfModels() {
        List<EventsModel> models = getModels();
        Mockito.when(eventsRepository.findAll()).thenReturn(models);
        actualResponse = eventsService.getAll();
        expectedResponse.setResponse(models);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getAll_returns_errorResponse_when_throwsException() {
        Mockito.when(eventsRepository.findAll()).thenThrow(new RuntimeException());
        actualResponse = eventsService.getAll();
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_getById_returns_model() {
        EventsModel model = getModel(id);
        Optional<EventsModel> modelOpt = Optional.of(model);
        Mockito.when(eventsRepository.findById(id)).thenReturn(modelOpt);
        actualResponse = eventsService.getById(id);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_noEntityFound() {
        Mockito.when(eventsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = eventsService.getById(id);
        expectedResponse.setResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_getById_returns_errorResponse_when_throwsException() {

        Mockito.when(eventsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = eventsService.getById(id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_createEvent_returns_event() {
        EventsModel model = getModel(id);
        Mockito.when(eventsRepository.save(model)).thenReturn(model);
        actualResponse = eventsService.create(model);
        expectedResponse.setResponse(model);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_createEvent_returns_errorResponse_when_throwsException() {
        EventsModel model = getModel(id);
        Mockito.when(eventsRepository.save(model)).thenThrow(new RuntimeException());
        actualResponse = eventsService.create(model);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updateEvent_returns_event() {
        Optional<EventsModel> modelBeforeUpdateOpt = Optional.of(getModel(id));
        EventsModel modelRequestToChangeName = EventsModel.builder()
                .name("name")
                .id(id)
                .date(LocalDateTime.now())
                .details("details")
                .build();
        Mockito.when(eventsRepository.findById(id)).thenReturn(modelBeforeUpdateOpt);
        Mockito.when(eventsRepository.save(any())).thenReturn(modelRequestToChangeName);
        actualResponse = eventsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(modelRequestToChangeName);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updateEvent_returns_noEntityFound() {
        EventsModel modelRequestToChangeName = EventsModel.builder()
                .name("name")
                .id(id)
                .date(LocalDateTime.now())
                .details("details")
                .build();
        Mockito.when(eventsRepository.findById(id)).thenReturn(Optional.empty());
        actualResponse = eventsService.update(modelRequestToChangeName, id);
        expectedResponse.setResponse(Constants.NO_ENTITY_FOUND_WITH_ID + id);
        expectedResponse.setResponseCode(ResponseCodes.SUCCESS);
        testSuccessScbResponse(expectedResponse, actualResponse);
    }

    @Test
    void test_updateEvent_returns_errorResponse_when_findById_throwsException() {
        EventsModel modelRequestToChangeName =  EventsModel.builder()
                .name("name")
                .id(id)
                .date(LocalDateTime.now())
                .details("details")
                .build();
        Mockito.when(eventsRepository.findById(id)).thenThrow(new RuntimeException());
        actualResponse = eventsService.update(modelRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }

    @Test
    void test_updateEvent_returns_errorResponse_when_save_throwsException() {
        Optional<EventsModel> userBeforeUpdateOpt = Optional.of(getModel(id));
        EventsModel userRequestToChangeName = EventsModel.builder()
                .name("name")
                .id(id)
                .date(LocalDateTime.now())
                .details("details")
                .build();
        Mockito.when(eventsRepository.findById(id)).thenReturn(userBeforeUpdateOpt);
        Mockito.when(eventsRepository.save(any())).thenThrow(new RuntimeException());
        actualResponse = eventsService.update(userRequestToChangeName, id);
        testExceptionScbResponse(actualResponse);
    }


    EventsModel getModel(int id) {
        return EventsModel.builder()
                .name("name")
                .id(id)
                .date(LocalDateTime.now())
                .details("details")
                .build();
    }


    List<EventsModel> getModels() {
        ArrayList<EventsModel> modelList = new ArrayList<>();
        modelList.add(getModel(1));
        modelList.add(getModel(2));
        return modelList;
    }


}
