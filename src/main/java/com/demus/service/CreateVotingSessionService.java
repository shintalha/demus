package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.CreateMusicPoolServiceResponse;
import com.demus.model.service.CreateVotingSessionServiceResponse;
import com.demus.model.service.GetPlaylistByIdServiceResponse;
import com.demus.model.service.GetPlaylistTracksServiceResponse;
import com.demus.model.spotify.PlaylistItem;
import com.demus.model.spotify.Track;
import com.demus.model.user.Voting;
import com.demus.model.user.VotingSession;
import com.demus.model.user.VotingTrack;
import com.demus.repository.VotingRepository;
import com.demus.repository.VotingSessionRepository;
import com.nimbusds.jose.shaded.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import com.demus.utils.VotingUtil;

@Service
public class CreateVotingSessionService {
    @Autowired
    VotingRepository votingRepository;
    @Autowired
    VotingSessionRepository votingSessionRepository;
    @Autowired
    GetPlaylistTracksService getPlaylistTracksService;
    @Autowired
    GetPlaylistsService getPlaylistsService;
    @SneakyThrows
    public CreateVotingSessionServiceResponse createVotingSession(String playlistId, String userId, String token) {
        CreateVotingSessionServiceResponse serviceResponse = new CreateVotingSessionServiceResponse();
        GetPlaylistByIdServiceResponse getPlaylistByIdServiceResponse = getPlaylistsService.getPlaylistById(playlistId, token);
        Set<Integer> randomlySelectedTrackOffsets = VotingUtil.getRandomNumber(getPlaylistByIdServiceResponse.getPlaylist().getTotal());
        GetPlaylistTracksServiceResponse getPlaylistTracksServiceResponse = getPlaylistTracksService.getPlaylistTracksByOffsets(token, playlistId, randomlySelectedTrackOffsets);
        Voting newVoting = VotingUtil.buildVoting(getPlaylistTracksServiceResponse.getTracks(), new Voting());
        newVoting.setVotingNumber(1);
        newVoting.setOwnerOfVoting(userId);
        newVoting.setVotingId(UUID.randomUUID().toString());
        VotingSession votingSession = new VotingSession(userId, newVoting.getVotingId());
        votingSessionRepository.save(votingSession);
        votingRepository.save(newVoting);
        return serviceResponse;
    }
}
