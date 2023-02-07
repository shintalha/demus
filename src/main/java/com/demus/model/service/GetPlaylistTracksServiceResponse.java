package com.demus.model.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPlaylistTracksServiceResponse extends ServiceResponse {
    private PlaylistTracks playlistTracks;
}
