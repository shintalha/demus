package com.demus.model.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateMusicPoolServiceResponse extends ServiceResponse {
    private String serializedMusicPool;
    private PlaylistTracks playlistTracks;

}
