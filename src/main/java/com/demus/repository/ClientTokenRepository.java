package com.demus.repository;

import com.demus.model.user.ClientToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTokenRepository extends CrudRepository<ClientToken, String> {
}
