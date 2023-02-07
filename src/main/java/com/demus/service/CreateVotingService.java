package com.demus.service;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.httpResponseEntity.UsersPlaylists;
import com.demus.model.service.CreateMusicPoolServiceResponse;
import com.demus.model.service.CreateVotingServiceResponse;
import com.demus.model.spotify.PlaylistItem;
import com.demus.model.user.Voting;
import com.demus.model.user.VotingSession;
import com.demus.repository.VotingSessionRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
public class CreateVotingService {
    @Autowired
    CreateMusicPoolService createMusicPoolService;
    @Autowired
    VotingSessionRepository votingSessionRepository;

    @SneakyThrows
    public CreateVotingServiceResponse createVoting(UsersPlaylists usersPlaylists, String userId, String token) {
        CreateVotingServiceResponse serviceResponse = new CreateVotingServiceResponse();
        CreateMusicPoolServiceResponse musicPoolServiceResponse = createMusicPoolService.createMusicPoolService(token, usersPlaylists);

        if (!musicPoolServiceResponse.isSuccess()) {
            serviceResponse.constructErrorResponse(serviceResponse.getMessage(), 500);
            return serviceResponse;
        }

        ArrayList<Integer> randomlySelectedTracks = getRandomNumber(musicPoolServiceResponse.getPlaylistTracks().getTotal());
        Voting newVoting = buildVoting(randomlySelectedTracks, musicPoolServiceResponse.getPlaylistTracks(), userId);
        newVoting.setMusicPool(musicPoolServiceResponse.getSerializedMusicPool());

        VotingSession votingSession = new VotingSession(userId, newVoting.getVotingId());
        votingSessionRepository.save(votingSession);
        return serviceResponse;
    }

    private ArrayList<Integer> getRandomNumber(int total) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i=0; i<total; i++) numbers.add(i);
        Collections.shuffle(numbers);
        return numbers;
    }

    private Voting buildVoting(ArrayList<Integer> selectedTracks, PlaylistTracks playlistTracks, String userId) {
        Voting voting = new Voting();
        ArrayList<PlaylistItem> playlistItems = playlistTracks.getItems();
        voting.setTrack1_Id(playlistItems.get(selectedTracks.get(0)).getTrack().getId());
        voting.setTrack1_Artist(playlistItems.get(selectedTracks.get(0)).getTrack().getArtists().get(0).getName());
        voting.setTrack1_Name(playlistItems.get(selectedTracks.get(0)).getTrack().getName());
        voting.setTrack1_image(playlistItems.get(selectedTracks.get(0)).getTrack().getAlbum().getImages().get(0).getUrl());
        voting.setTrack1_Votes(0);

        voting.setTrack2_Id(playlistItems.get(selectedTracks.get(1)).getTrack().getId());
        voting.setTrack2_Artist(playlistItems.get(selectedTracks.get(1)).getTrack().getArtists().get(0).getName());
        voting.setTrack2_Name(playlistItems.get(selectedTracks.get(1)).getTrack().getName());
        voting.setTrack2_image(playlistItems.get(selectedTracks.get(1)).getTrack().getAlbum().getImages().get(0).getUrl());
        voting.setTrack2_Votes(0);

        voting.setTrack3_Id(playlistItems.get(selectedTracks.get(2)).getTrack().getId());
        voting.setTrack3_Artist(playlistItems.get(selectedTracks.get(2)).getTrack().getArtists().get(0).getName());
        voting.setTrack3_Name(playlistItems.get(selectedTracks.get(2)).getTrack().getName());
        voting.setTrack3_image(playlistItems.get(selectedTracks.get(2)).getTrack().getAlbum().getImages().get(0).getUrl());
        voting.setTrack3_Votes(0);

        voting.setTrack4_Id(playlistItems.get(selectedTracks.get(3)).getTrack().getId());
        voting.setTrack4_Artist(playlistItems.get(selectedTracks.get(3)).getTrack().getArtists().get(0).getName());
        voting.setTrack4_Name(playlistItems.get(selectedTracks.get(3)).getTrack().getName());
        voting.setTrack4_image(playlistItems.get(selectedTracks.get(3)).getTrack().getAlbum().getImages().get(0).getUrl());
        voting.setTrack4_Votes(0);

        voting.setTrack5_Id(playlistItems.get(selectedTracks.get(4)).getTrack().getId());
        voting.setTrack5_Artist(playlistItems.get(selectedTracks.get(4)).getTrack().getArtists().get(0).getName());
        voting.setTrack5_Name(playlistItems.get(selectedTracks.get(4)).getTrack().getName());
        voting.setTrack5_image(playlistItems.get(selectedTracks.get(4)).getTrack().getAlbum().getImages().get(0).getUrl());
        voting.setTrack5_Votes(0);

        voting.setVotingId(UUID.randomUUID().toString());
        voting.setVotingNumber(0);
        voting.setOwnerOfVoting(userId);

        return voting;
    }
}