package com.demus.repository;

import com.demus.model.user.Voting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingRepository extends CrudRepository<Voting, String> {
}
