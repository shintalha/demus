package com.demus.model.service;

import com.demus.model.spotify.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetPlaylistByIdServiceResponse extends ServiceResponse {
    private Playlist playlist;
}
