package com.demus.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("VotingSession")
@Getter
@Setter
@AllArgsConstructor
public class VotingSession {
    @Id
    private String userId;
    private String votingId;
}
