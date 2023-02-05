package com.demus.model.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.io.Serializable;

@RedisHash("User")
@Getter
@Setter
@AllArgsConstructor
public class User implements Serializable {
    @Id
    private String id;
    private String username;
    private String imageUri;
    private String accessToken;
    private String refreshToken;
}
