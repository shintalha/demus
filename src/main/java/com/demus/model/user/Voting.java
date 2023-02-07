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
    private String votingId;
    private String ownerOfVoting;
    private Integer votingNumber;
    private String musicPool;

    private String track1_Id;
    private String track1_Artist;
    private String track1_Name;
    private String track1_image;
    private Integer track1_Votes;

    private String track2_Id;
    private String track2_Artist;
    private String track2_Name;
    private String track2_image;
    private Integer track2_Votes;

    private String track3_Id;
    private String track3_Artist;
    private String track3_Name;
    private String track3_image;
    private Integer track3_Votes;

    private String track4_Id;
    private String track4_Artist;
    private String track4_Name;
    private String track4_image;
    private Integer track4_Votes;

    private String track5_Id;
    private String track5_Artist;
    private String track5_Name;
    private String track5_image;
    private Integer track5_Votes;
}
