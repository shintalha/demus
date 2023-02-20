package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.GetPlaylistTracksServiceResponse;
import com.demus.model.service.GetUsersPlaylistsServiceResponse;
import com.demus.model.spotify.Track;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.demus.constants.ConstantParameter.*;

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

    @SneakyThrows
    public GetPlaylistTracksServiceResponse getPlaylistTracksByOffsets(String token, String playlistId, Set<Integer> offsets) {
        GetPlaylistTracksServiceResponse serviceResponse = new GetPlaylistTracksServiceResponse();
        Set<Track> tracks = new HashSet<Track>();
        for (Integer offset : offsets) {
            ResponseEntity<String> response = spotifyRequestService.fetch(token, GET_PLAYLIST_TRACK_BY_OFFSET_ROUTE.replace("playlist_id", playlistId) + offset.toString(), HttpMethod.GET);
            if (response.getStatusCode().is2xxSuccessful()) {
                PlaylistTracks playlistTracks = new Gson().fromJson(response.getBody(), PlaylistTracks.class);
                tracks.add(playlistTracks.getItems().get(0).getTrack());
            }
        }
        if (!tracks.isEmpty()) {
            serviceResponse.constructSuccessResponse(200);
            serviceResponse.setTracks(tracks);
        }
        else {
            serviceResponse.constructErrorResponse(500);
        }
        return serviceResponse;
    }
}
