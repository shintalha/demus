package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.service.GetPlaylistByIdServiceResponse;
import com.demus.model.service.GetPlaylistTracksServiceResponse;
import com.demus.model.service.GetUsersPlaylistsServiceResponse;
import com.demus.model.service.ServiceResponse;
import com.demus.model.spotify.PlaylistItem;
import com.demus.model.spotify.Track;
import com.demus.model.user.Voting;
import com.demus.model.user.VotingTrack;
import com.demus.repository.UserRepository;
import com.demus.repository.VotingRepository;
import com.demus.repository.VotingSessionRepository;
import com.demus.utils.VotingUtil;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demus.utils.VotingUtil;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NextVotingService {
    @Autowired
    VotingSessionRepository votingSessionRepository;
    @Autowired
    VotingRepository votingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    GetPlaylistsService getPlaylistsService;
    @Autowired
    GetPlaylistTracksService getPlaylistTracksService;

    @SneakyThrows
    public ServiceResponse nextVoting(String votingId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Voting voting = votingRepository.findById(votingId).orElseThrow();
        String accessToken = userRepository.findById(voting.getOwnerOfVoting()).get().getAccessToken();
        GetPlaylistByIdServiceResponse getPlaylistByIdServiceResponse = getPlaylistsService.getPlaylistById(voting.getPlaylistId(), accessToken);
        Set<Integer> randomlySelectedTrackOffsets = VotingUtil.getRandomNumber(getPlaylistByIdServiceResponse.getPlaylist().getTotal());
        GetPlaylistTracksServiceResponse getPlaylistTracksServiceResponse = getPlaylistTracksService.getPlaylistTracksByOffsets(accessToken, voting.getPlaylistId(), randomlySelectedTrackOffsets);
        Voting nextVoting = VotingUtil.buildVoting(getPlaylistTracksServiceResponse.getTracks(), voting);
        nextVoting.setVotingNumber(voting.getVotingNumber() + 1);
        votingRepository.save(nextVoting);
        serviceResponse.constructSuccessResponse(200);
        return serviceResponse;
    }

}
