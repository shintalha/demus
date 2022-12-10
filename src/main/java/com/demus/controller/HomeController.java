package com.demus.controller;

import com.demus.model.httpResponse.CurrentlyPlayingControllerResponse;
import com.demus.model.httpResponseEntity.CurrentlyPlaying;
import com.demus.service.SpotifyRequestService;
import com.nimbusds.jose.shaded.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @Autowired
    private SpotifyRequestService spotifyRequestService;


    @GetMapping
    public Map<String, Object> getToken(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }

    @GetMapping("currently-playing")
    public CurrentlyPlayingControllerResponse getCurrentlyPlaying(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        CurrentlyPlayingControllerResponse controllerResponse = new CurrentlyPlayingControllerResponse();
        try {
            OAuth2AuthorizedClient authorizedClient = this.authorizedClientService.loadAuthorizedClient("spotify", oAuth2AuthenticationToken.getName());
            String token = authorizedClient.getAccessToken().getTokenValue();
            ResponseEntity<String> response = spotifyRequestService.fetch(token, "/v1/me/player/currently-playing", HttpMethod.GET);
            if (response.getStatusCode().is1xxInformational()) {
                controllerResponse.buildServiceError();
                return controllerResponse;
            }
            if (response.hasBody()) {
                CurrentlyPlaying currentlyPlaying = new Gson().fromJson(response.getBody(), CurrentlyPlaying.class);
                controllerResponse.setCurrentlyPlaying(currentlyPlaying);
                controllerResponse.currentlyListening();
            } else {
                controllerResponse.notListeningCurently();
            }
        } catch (Exception ex) {
            controllerResponse.buildControllerError();
        }
        return controllerResponse;
    }

}
