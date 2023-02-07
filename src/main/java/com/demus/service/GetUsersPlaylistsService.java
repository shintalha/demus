package com.demus.service;

import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.GetUsersPlaylistsServiceResponse;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.demus.constants.ConstantParameter.GET_PLAYLISTS_ROUTE;

@Service
public class GetUsersPlaylistsService {
    @Autowired
    SpotifyRequestService spotifyRequestService;

    @SneakyThrows
    public GetUsersPlaylistsServiceResponse getUsersPlaylists(String token) {
        GetUsersPlaylistsServiceResponse serviceResponse = new GetUsersPlaylistsServiceResponse();
        ResponseEntity<String> response = spotifyRequestService.fetch(token, GET_PLAYLISTS_ROUTE, HttpMethod.GET);
        if (response.getStatusCode().is2xxSuccessful()) {
            UsersPlaylists usersPlaylists = new Gson().fromJson(response.getBody(), UsersPlaylists.class);
            serviceResponse.setUsersPlaylists(usersPlaylists);
            serviceResponse.constructSuccessResponse(response.getStatusCode().value());
        }
        else {
            serviceResponse.constructErrorResponse(response.getStatusCode().value());
        }
        return serviceResponse;
    }
}
