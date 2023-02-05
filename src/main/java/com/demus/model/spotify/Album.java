package com.demus.model.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class Album{
    private String album_type;
    private ArrayList<Artist> artists;
    private ArrayList<String> available_markets;
    private ExternalUrls external_urls;
    private String href;
    private String id;
    private ArrayList<Image> images;
    private String name;
    private String release_date;
    private String release_date_precision;
    private int total_tracks;
    private String type;
    private String uri;
}
