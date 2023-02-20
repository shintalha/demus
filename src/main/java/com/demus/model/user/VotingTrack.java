package com.demus.model.user;

import lombok.Data;

@Data
public class VotingTrack {
    private String id;
    private String artist;
    private String name;
    private String image;
    private Integer votes;
}
