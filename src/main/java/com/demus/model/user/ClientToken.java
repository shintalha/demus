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
@RedisHash("UserToken")
public class ClientToken {
    @Id
    private String id;
    private String accessToken;
    private String accessTokenExpiresAt;
    private String refreshToken;

    @TimeToLive
    private Long expiration;
}
