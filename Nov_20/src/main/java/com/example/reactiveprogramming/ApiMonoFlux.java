package com.example.reactiveprogramming;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ApiMonoFlux {

    record Uni(int id, String name, String email) {}

    // Must be initialized properly
    private final List<Uni> users = new ArrayList<>();

    // Constructor or @PostConstruct to add initial data
    public ApiMonoFlux() {
        users.add(new Uni(1, "hello", "hello@example.com"));
        users.add(new Uni(2, "bye", "bye@example.com"));
    }

    @GetMapping("/get/{id}")
    public Mono<Uni> getById(@PathVariable int id) {
        return Mono.justOrEmpty(
            users.stream()
                 .filter(user -> user.id() == id)
                 .findFirst()
        );
    }

    @GetMapping("/users")  // Fixed: was missing "/"
    public Flux<Uni> getUsers() {
        return Flux.fromIterable(users);
    }

    @PostMapping("/add")
    public Mono<Uni> addUser(@RequestBody Uni user) {
        int newId = users.size() + 1;
        Uni newUser = new Uni(newId, user.name(), user.email());
        
        users.add(newUser);  // Add the one with generated ID
        
        return Mono.just(newUser);
    }
}
