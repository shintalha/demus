package com.demus.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.List;

@RedisHash("Voting")
@Getter
@Setter
public class Voting implements Serializable {
    @Id
    private int votingId;

    private String track1Id;
    private Integer votesOfTrack1;

    private String track2Id;
    private Integer votesOfTrack2;

    private String track3Id;
    private Integer votesOfTrack3;

    private String track4Id;
    private Integer votesOfTrack4;

    private String track5Id;
    private Integer votesOfTrack5;

    @TimeToLive
    private Long expiration;
}
