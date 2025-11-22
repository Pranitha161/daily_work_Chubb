package com.example.demo.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.entity.Passenger;
import com.example.demo.repository.PassengerRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class PassengerService {

    private final PassengerRepository passengerRepo;
    public Mono<Passenger> getPassengerById(String passengerId) {
        return passengerRepo.findById(passengerId);
    }

    public Mono<Passenger> getPassengerByEmail(String email) {
        return passengerRepo.findByEmail(email);
    }
	public Mono<Passenger> savePassenger(Passenger passenger){
	    return passengerRepo.findByEmail(passenger.getEmail())
	            .flatMap(exists -> Mono.<Passenger>error(
	                new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email already registered")
	            ))
	            .switchIfEmpty(passengerRepo.save(passenger));  
	}



    public Mono<Passenger> updateById(Passenger p, String id) {
        return passengerRepo.findById(id)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "No passenger found with id: " + id))))
            .flatMap(existing -> {
                if (p.getName() != null) existing.setName(p.getName());
                if (p.getEmail() != null) existing.setEmail(p.getEmail());
                if (p.getAge() != 0) existing.setAge(p.getAge());
                return passengerRepo.save(existing);
            });
    }
    public Mono<Void> deleteById(String id) {
        return passengerRepo.findById(id)
            .switchIfEmpty(Mono.defer(() -> Mono.error(new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "No passenger found with id: " + id))))
            .flatMap(passenger -> passengerRepo.delete(passenger));
    }
}
