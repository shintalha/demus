package com.demus.utils;

import com.demus.model.httpResponseEntity.PlaylistTracks;
import com.demus.model.spotify.PlaylistItem;
import com.demus.model.user.Voting;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VotingUtil {
    public Voting buildVoting(List<Integer> selectedTracks, PlaylistTracks playlistTracks, Voting voting) {
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

        return voting;
    }
}
