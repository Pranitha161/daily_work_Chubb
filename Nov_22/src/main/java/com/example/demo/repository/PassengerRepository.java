package com.example.demo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.example.demo.entity.Passenger;

import reactor.core.publisher.Mono;

public interface PassengerRepository extends ReactiveMongoRepository<Passenger, String>{
	Mono<Passenger> findByEmail(String email);
}
