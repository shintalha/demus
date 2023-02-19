package com.demus.controller;

import com.demus.model.VotingMessage;
import com.demus.model.user.Voting;
import com.demus.repository.VotingRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    VotingRepository votingRepository;
    @MessageMapping("/votes")
    public String broadcastVoting(@Payload String message) {
        String broadcastMessage = "unvalid_number";
        VotingMessage votingMessage = new Gson().fromJson(message, VotingMessage.class);
        Voting voting = votingRepository.findById(votingMessage.getVotingId()).orElse(null);
        if (votingMessage.getVotedTrackNumber().equals(1))
            voting.setTrack1_Votes(voting.getTrack1_Votes() + 1);
        else if (votingMessage.getVotedTrackNumber().equals(2))
            voting.setTrack2_Votes(voting.getTrack2_Votes() + 1);
        else if (votingMessage.getVotedTrackNumber().equals(3))
            voting.setTrack3_Votes(voting.getTrack3_Votes() + 1);
        else if (votingMessage.getVotedTrackNumber().equals(4))
            voting.setTrack4_Votes(voting.getTrack4_Votes() + 1);
        else if (votingMessage.getVotedTrackNumber().equals(5))
            voting.setTrack5_Votes(voting.getTrack5_Votes() + 1);
        else
            return broadcastMessage;

        broadcastMessage = new Gson().toJson(voting);
        this.simpMessagingTemplate.convertAndSendToUser(voting.getOwnerOfVoting(),"/queue/votes", broadcastMessage);
        return broadcastMessage;
    }
}
