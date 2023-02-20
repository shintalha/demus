package com.demus.model.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.spotify.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPlaylistTracksServiceResponse extends ServiceResponse {
    private PlaylistTracks playlistTracks;
    private Set<Track> tracks;
}
