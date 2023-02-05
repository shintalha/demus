package com.demus.model.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class SpotifyUser {
    private String display_name;
    private ExternalUrls external_urls;
    private Followers followers;
    private String href;
    private String id;
    private ArrayList<Image> images;
    private String type;
    private String uri;
}
