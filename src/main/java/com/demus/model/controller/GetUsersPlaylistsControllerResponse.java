package com.demus.model.controller;

import com.demus.model.httpResponseEntity.UsersPlaylists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersPlaylistsControllerResponse extends ControllerResponse {
    private UsersPlaylists usersPlaylists;
}
