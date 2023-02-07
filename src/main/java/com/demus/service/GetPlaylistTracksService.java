package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.GetPlaylistTracksServiceResponse;
import com.demus.model.service.GetUsersPlaylistsServiceResponse;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static com.demus.constants.ConstantParameter.GET_PLAYLISTS_ROUTE;
import static com.demus.constants.ConstantParameter.GET_PLAYLIST_TRACKS_ROUTE;

@Service
public class GetPlaylistTracksService {
    @Autowired
    SpotifyRequestService spotifyRequestService;

    @SneakyThrows
    public GetPlaylistTracksServiceResponse getPlaylistTracks(String token, String playlistId) {
        GetPlaylistTracksServiceResponse serviceResponse = new GetPlaylistTracksServiceResponse();
        ResponseEntity<String> response = spotifyRequestService.fetch(token, GET_PLAYLIST_TRACKS_ROUTE.replace("playlist_id", playlistId), HttpMethod.GET);
        if (response.getStatusCode().is2xxSuccessful()) {
            PlaylistTracks playlistTracks = new Gson().fromJson(response.getBody(), PlaylistTracks.class);
            serviceResponse.setPlaylistTracks(playlistTracks);
            serviceResponse.constructSuccessResponse(response.getStatusCode().value());
        }
        else {
            serviceResponse.constructErrorResponse(response.getStatusCode().value());
        }
        return serviceResponse;
    }
}
