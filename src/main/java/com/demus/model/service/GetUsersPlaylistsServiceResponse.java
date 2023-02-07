package com.demus.model.service;

import com.demus.model.httpResponseEntity.UsersPlaylists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetUsersPlaylistsServiceResponse extends ServiceResponse {
    private UsersPlaylists usersPlaylists;
}
