package com.demus.service;

import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.GetPlaylistByIdServiceResponse;
import com.demus.model.service.GetUsersPlaylistsServiceResponse;
import com.demus.model.spotify.Playlist;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.demus.constants.ConstantParameter.*;

@Service
public class GetPlaylistsService {
    @Autowired
    SpotifyRequestService spotifyRequestService;

    @SneakyThrows
    public GetUsersPlaylistsServiceResponse getUsersPlaylists(String token) {
        GetUsersPlaylistsServiceResponse serviceResponse = new GetUsersPlaylistsServiceResponse();
        ResponseEntity<String> response = spotifyRequestService.fetch(token, GET_CURRENT_USER_PLAYLISTS_ROUTE, HttpMethod.GET);
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

    public GetPlaylistByIdServiceResponse getPlaylistById(String playlistId, String token) {
        GetPlaylistByIdServiceResponse serviceResponse = new GetPlaylistByIdServiceResponse();
        ResponseEntity<String> response = spotifyRequestService.fetch(token, GET_PLAYLIST_ROUTE + playlistId + "?fields=tracks.total", HttpMethod.GET);
        if (response.getStatusCode().is2xxSuccessful()) {
            Playlist playlist = new Gson().fromJson(response.getBody(), Playlist.class);
            serviceResponse.setPlaylist(playlist);
            serviceResponse.constructSuccessResponse(response.getStatusCode().value());
        }
        else {
            serviceResponse.constructErrorResponse(response.getStatusCode().value());
        }
        return serviceResponse;
    }


}
