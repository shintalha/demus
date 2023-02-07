package com.demus.repository;

import com.demus.model.user.VotingSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotingSessionRepository extends CrudRepository<VotingSession, String> {
}
