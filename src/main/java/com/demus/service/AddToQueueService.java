package com.demus.service;

import com.demus.model.httpResponseEntity.CurrentlyPlaying;
import com.demus.model.service.CurrentlyPlayingServiceResponse;
import com.demus.model.service.ServiceResponse;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.demus.constants.ConstantParameter.ADD_TO_QUEUE_ROUTE;

@Service
public class AddToQueueService {
    @Autowired
    SpotifyRequestService spotifyRequestService;

    public ServiceResponse addToQueue(String token, String uri) {
        ServiceResponse serviceResponse = new ServiceResponse();
        ResponseEntity<String> response = spotifyRequestService.fetch(token, ADD_TO_QUEUE_ROUTE + uri, HttpMethod.GET);
        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            serviceResponse.constructSuccessResponse(response.getStatusCode().value());
        }
        else {
            serviceResponse.constructErrorResponse(response.getStatusCode().value());
        }
        return serviceResponse;
    }
}
