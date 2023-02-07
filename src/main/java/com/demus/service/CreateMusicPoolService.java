package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.CreateMusicPoolServiceResponse;
import com.demus.model.service.GetPlaylistTracksServiceResponse;
import com.demus.model.spotify.Playlist;
import com.demus.model.spotify.PlaylistItem;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CreateMusicPoolService {
    @Autowired
    GetPlaylistTracksService getPlaylistTracksService;

    @SneakyThrows
    public CreateMusicPoolServiceResponse createMusicPoolService(String token, UsersPlaylists usersPlaylists) {
        CreateMusicPoolServiceResponse serviceResponse = new CreateMusicPoolServiceResponse();
        PlaylistTracks mixedPlaylist = new PlaylistTracks();
        ArrayList<PlaylistItem> mixedPlaylistItems = new ArrayList<>();
        for(Playlist playlist : usersPlaylists.getItems()) {
            GetPlaylistTracksServiceResponse playlistTracksServiceResponse = getPlaylistTracksService.getPlaylistTracks(token, playlist.getId());
            if(playlistTracksServiceResponse.isSuccess()) {
                mixedPlaylistItems.addAll(playlistTracksServiceResponse.getPlaylistTracks().getItems());
                mixedPlaylist.setTotal(mixedPlaylist.getTotal() + playlistTracksServiceResponse.getPlaylistTracks().getTotal());
            }
        }
        if (mixedPlaylistItems.isEmpty()) {
            serviceResponse.constructErrorResponse("Playlists cannot be retrieved", serviceResponse.getStatusCode());
            return serviceResponse;
        }
        mixedPlaylist.setItems(mixedPlaylistItems);
        String serializedPlaylistItems = new Gson().toJson(mixedPlaylist);
        serviceResponse.setPlaylistTracks(mixedPlaylist);
        serviceResponse.setSerializedMusicPool(serializedPlaylistItems);
        serviceResponse.constructSuccessResponse(200);
        return serviceResponse;
    }
}
