package com.demus.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Setter
@AllArgsConstructor
@RedisHash("Session")
public class Session {
    @Id
    private String uuid;
    private String spotifyId;

    @TimeToLive
    private Long expiration;
}
