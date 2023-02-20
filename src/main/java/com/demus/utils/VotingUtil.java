package com.demus.utils;

import com.demus.model.spotify.Track;
import com.demus.model.user.Voting;
import com.demus.model.user.VotingTrack;
import com.nimbusds.jose.shaded.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class VotingUtil {
    public static Set<Integer> getRandomNumber(int total) {
        Set<Integer> numbers = new Random().ints(0, total)
                .distinct()
                .limit(5)
                .boxed()
                .collect(Collectors.toSet());
        return numbers;
    }

    public static Voting buildVoting(Set<Track> tracks, Voting voting) {
        List<VotingTrack> votingTracks = new ArrayList<>();
        for (Track track : tracks) {
            VotingTrack votingTrack = new VotingTrack();
            votingTrack.setVotes(0);
            votingTrack.setId(track.getId());
            votingTrack.setName(track.getName());
            votingTrack.setArtist(track.getArtists().get(0).getName());
            votingTrack.setImage(track.getAlbum().getImages().get(0).getUrl());
            votingTracks.add(votingTrack);
        }
        String serializedVoting = new Gson().toJson(votingTracks);
        voting.setVotingInfo(serializedVoting);
        return voting;
    }
}
