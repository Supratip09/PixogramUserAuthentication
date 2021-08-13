package com.ibm.fsd.training.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ibm.fsd.training.model.User;

import reactor.core.publisher.Mono;

public interface ReactiveUserRepository extends ReactiveMongoRepository<User, String> {

	Mono<UserDetails> findByUsername(String username);
}