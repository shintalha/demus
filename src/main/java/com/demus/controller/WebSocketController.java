package com.demus.controller;

import com.demus.model.VotingMessage;
import com.demus.model.user.Voting;
import com.demus.model.user.VotingTrack;
import com.demus.repository.VotingRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@RestController
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    VotingRepository votingRepository;
    @MessageMapping("/votes")
    public void broadcastVoting(@Payload String message) {
        String broadcastMessage = "unvalid_number";
        VotingMessage votingMessage = new Gson().fromJson(message, VotingMessage.class);
        Voting voting = votingRepository.findById(votingMessage.getVotingId()).orElse(null);
        ArrayList<VotingTrack> votingTracks = new Gson().fromJson(voting.getVotingInfo(), new TypeToken<ArrayList<VotingTrack>>(){}.getType());

        votingTracks.get(votingMessage.getVotedTrackNumber()).setVotes(votingTracks.get(votingMessage.getVotedTrackNumber()).getVotes() + 1);
        String votingInfo = new Gson().toJson(votingTracks);
        voting.setVotingInfo(votingInfo);
        votingRepository.save(voting);
        this.simpMessagingTemplate.convertAndSendToUser(voting.getOwnerOfVoting(),"/queue/votes", votingInfo);
    }
}
