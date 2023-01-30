package com.demus.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @MessageMapping("/votes")
    @SendTo("/voting/votes")
    public String broadcastVotes(@Payload String message) {
        return message;
    }
}
