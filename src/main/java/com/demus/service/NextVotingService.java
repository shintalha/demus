package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.service.ServiceResponse;
import com.demus.model.spotify.PlaylistItem;
import com.demus.model.user.Voting;
import com.demus.repository.VotingRepository;
import com.demus.repository.VotingSessionRepository;
import com.demus.utils.VotingUtil;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class NextVotingService {
    @Autowired
    VotingSessionRepository votingSessionRepository;
    @Autowired
    VotingRepository votingRepository;
    @Autowired
    VotingUtil votingUtil;

    @SneakyThrows
    public  ServiceResponse nextVoting(String votingId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Voting voting = votingRepository.findById(votingId).orElseThrow();
        String musicPool = voting.getMusicPool();
        PlaylistTracks playlistTracks = new Gson().fromJson(musicPool, PlaylistTracks.class);
        List<Integer> randomlySelectedNumbers = getRandomNumbers(playlistTracks.getTotal());
        Voting nextVoting = votingUtil.buildVoting(randomlySelectedNumbers, playlistTracks, voting);
        nextVoting.setVotingNumber(voting.getVotingNumber() + 1);
        votingRepository.save(nextVoting);
        serviceResponse.constructSuccessResponse(200);
        return serviceResponse;
    }

    private List<Integer> getRandomNumbers(int total) {
        List<Integer> numbers = new Random().ints(0, total)
                .distinct()
                .limit(5)
                .boxed()
                .collect(Collectors.toList());
        return numbers;
    }

}
