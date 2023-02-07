package com.demus.model.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateVotingServiceResponse extends ServiceResponse {
    private String votingId;
    private String ownerOfVoting;
}
