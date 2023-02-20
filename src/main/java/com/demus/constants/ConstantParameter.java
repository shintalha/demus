package com.demus.constants;

import java.time.Duration;

public class ConstantParameter {
    public static final String OAUTH_COOKIE_NAME = "OAUTH";
    public static final String SESSION_COOKIE_NAME = "SESSION";

    public static final String CURRENTLY_PLAYING_ROUTE = "/v1/me/player/currently-playing";

    public static final String ADD_TO_QUEUE_ROUTE = "/v1/me/player/queue?uri=";
    public static final String GET_CURRENT_USER_PLAYLISTS_ROUTE = "/v1/me/playlists";

    public static final String GET_PLAYLIST_ROUTE = "/v1/playlists/";
    public static final String GET_PLAYLIST_TRACKS_ROUTE = "/v1/playlists/playlist_id/tracks";

    public static final String GET_PLAYLIST_TRACK_BY_OFFSET_ROUTE = "/v1/playlists/playlist_id/tracks?limit=1&offset=";

}
