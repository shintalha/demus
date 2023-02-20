package com.demus.model.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RedisHash("Voting")
@Getter
@Setter
public class Voting implements Serializable {
    @Id
    private String votingId;
    private String ownerOfVoting;
    private Integer votingNumber;
    private String playlistId;
    private String votingInfo;
}
