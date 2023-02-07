package com.demus.model.controller;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPlaylistTracksControllerResponse extends ControllerResponse {
    private PlaylistTracks playlistTracks;
}
