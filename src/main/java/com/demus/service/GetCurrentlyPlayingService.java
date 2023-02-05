package com.demus.service;

import com.demus.model.service.CurrentlyPlayingServiceResponse;
import com.demus.model.httpResponseEntity.CurrentlyPlaying;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.demus.constants.ConstantParameter.CURRENTLY_PLAYING_ROUTE;

@Service
public class GetCurrentlyPlayingService {
    @Autowired
    SpotifyRequestService spotifyRequestService;

    @SneakyThrows
    public CurrentlyPlayingServiceResponse getCurrentlyPlaying(String token) {
        CurrentlyPlayingServiceResponse serviceResponse = new CurrentlyPlayingServiceResponse();
        ResponseEntity<String> response = spotifyRequestService.fetch(token, CURRENTLY_PLAYING_ROUTE, HttpMethod.GET);
        if (response.getStatusCode().is2xxSuccessful() && response.hasBody()) {
            CurrentlyPlaying currentlyPlaying = new Gson().fromJson(response.getBody(), CurrentlyPlaying.class);
            serviceResponse.setCurrentlyPlaying(currentlyPlaying);
            serviceResponse.constructSuccessResponse(response.getStatusCode().value());
        }
        else {
            serviceResponse.constructErrorResponse(response.getStatusCode().value());
        }
        return serviceResponse;
    }
}
