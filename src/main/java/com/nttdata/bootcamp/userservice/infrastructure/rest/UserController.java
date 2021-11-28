package com.nttdata.bootcamp.userservice.infrastructure.rest;

import com.nttdata.bootcamp.userservice.application.UserOperations;
import com.nttdata.bootcamp.userservice.domain.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private static final String USER_SERVICE = "userService";
    private final UserOperations operations;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Flux<User>>> get() {
        return Mono.just(ResponseEntity.ok(operations.queryAll()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name=USER_SERVICE, fallbackMethod = "userFallback")
    public Mono<ResponseEntity<User>> get(@PathVariable Integer id) {
        return Mono.just(id)
                .flatMap(operations::findById)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name=USER_SERVICE, fallbackMethod = "userFallback")
    public Mono<ResponseEntity<User>> post(@RequestBody User user) {
        return operations.findById(user.getId())
                .switchIfEmpty(Mono.just(user)
                        .flatMap(operations::create))
                .map(this::postResponse);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name=USER_SERVICE, fallbackMethod = "userFallback")
    public Mono<ResponseEntity<User>> put(@PathVariable Integer id, @RequestBody User user) {
        return operations.findById(id)
                .flatMap(u-> operations.update(id, user))
                .map(this::postResponse)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorReturn(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable Integer id) {
        return operations.findById(id)
                .flatMap(user -> operations.delete(user.getId())
                        .thenReturn(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private ResponseEntity<User> postResponse(User user) {
        return ResponseEntity.created(URI.create("/users/" + user.getId().toString())).body(user);
    }

    public Mono<ResponseEntity<User>> userFallback(Exception e) {
        log.error(e.toString());
        return Mono.just(new ResponseEntity<>(new User(), HttpStatus.SERVICE_UNAVAILABLE));
    }

}
