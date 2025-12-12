package com.flightapp.demo.repository;


import com.flightapp.demo.entity.AirLineFile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AirLineFileRepository extends ReactiveMongoRepository<AirLineFile, String> {
}

