package com.demus.model.httpResponseEntity;

import com.demus.model.spotify.Playlist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersPlaylists {
    private Integer total;
    private ArrayList<Playlist> items;
}
