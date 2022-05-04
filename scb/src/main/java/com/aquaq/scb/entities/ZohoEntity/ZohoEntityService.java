package com.aquaq.scb.entities.ZohoEntity;

import com.aquaq.scb.entities.users.UsersModel;
import com.aquaq.scb.response.ScbResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import com.aquaq.scb.config.security.oauth.oauth2.OAuthTokenHolder;
import reactor.core.publisher.Mono;

import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
public class ZohoEntityService {

    private WebClient webClient;


    public ZohoEntityService(){

        webClient = WebClient.builder().baseUrl("https://people.zoho.com").build();

    }

    public ScbResponse getThankYous(String Authorization){
        try{
            WebClient.ResponseSpec response = webClient.get().uri("/people/api/forms/Thank_You_View/records?Index=1&rec_limit=199")
                    .headers(httpHeaders -> httpHeaders.set("Authorization","Zoho-oauthtoken " + Authorization))
                    .retrieve();
            String responseBody = response.bodyToMono(String.class).block();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(responseBody);
            List<ZohoThankYouModel> models = mapThankYouModels(jsonNode);
            return ScbResponse.createSuccessResponse(models);
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }

    public ScbResponse getAnnouncements(String Authorization){
        try{
        WebClient.ResponseSpec response = webClient.get().uri("/people/api/announcement/getAllAnnouncement?startIdx=1&isSticky")
                .headers(httpHeaders -> httpHeaders.set("Authorization","Zoho-oauthtoken " + Authorization))
                .retrieve();
        String responseBody = response.bodyToMono(String.class).block();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(responseBody);
        return ScbResponse.createSuccessResponse(jsonNode.toString());
        }catch(Exception e){
            return ScbResponse.createExceptionResponse(e);
        }
    }


    public List<ZohoThankYouModel> mapThankYouModels(JsonNode rawResponse){
        List<ZohoThankYouModel> models = new ArrayList<>();
        try {
            for (JsonNode node : rawResponse
            ) {
                ZohoThankYouModel model = new ObjectMapper().treeToValue(node, ZohoThankYouModel.class);
                models.add(model);
            }
        }catch (IllegalArgumentException | JsonProcessingException ec){
            System.out.println(ec.getMessage());
        }
        return models;
    }
}
