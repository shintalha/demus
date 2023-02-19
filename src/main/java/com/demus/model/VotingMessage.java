package com.demus.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class VotingMessage implements Serializable {
    private Integer votedTrackNumber;
    private String votingId;
}
