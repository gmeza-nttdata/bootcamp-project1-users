package com.nttdata.bootcamp.userservice.infrastructure.rest;

import com.nttdata.bootcamp.userservice.application.UserOperations;
import com.nttdata.bootcamp.userservice.domain.User;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;


/** Rest CRUD controller.
 *
 * @author Gustavo Meza
 *
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    /**
     * Name of the service fot the circuit breaker.
     */
    private static final String USER_SERVICE = "userService";
    /**
     * Operations for the user.
     */
    private final UserOperations operations;

    /** Get all users' method.
     * @return list of users
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Flux<User>>> get() {
        return Mono.just(ResponseEntity.ok(operations.queryAll()));
    }


    /** Get one user by its id.
     *
     * @param   id  id of the user
     * @return      ResponseEntity of the user
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "userFallback")
    public Mono<ResponseEntity<User>> get(@PathVariable final Integer id) {
        return Mono.just(id)
                .flatMap(operations::findById)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    /** Create a new user.
     *
     * @param user  user entity
     * @return      created user
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "userFallback")
    public Mono<ResponseEntity<User>> post(@RequestBody final User user) {
        return operations.findById(user.getId())
                .switchIfEmpty(Mono.just(user)
                        .flatMap(operations::create))
                .map(this::postResponse);
    }

    /** Update user.
     *
     * @param id    id of the user to be updated
     * @param user  user entity with the attributes to update
     * @return      updated user
     */
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @CircuitBreaker(name = USER_SERVICE, fallbackMethod = "userFallback")
    public Mono<ResponseEntity<User>> put(@PathVariable final Integer id,
                                          @RequestBody final User user) {
        return operations.findById(id)
                .flatMap(u -> operations.update(id, user))
                .map(this::postResponse)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .onErrorReturn(ResponseEntity.badRequest().build());
    }

    /** Delete a user.
     *
     * @param id    id of the user to be deleted
     * @return      no content
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable final Integer id) {
        return operations.findById(id)
                .flatMap(user -> operations.delete(user.getId())
                        .thenReturn(new
                                ResponseEntity<Void>(HttpStatus.NO_CONTENT)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private ResponseEntity<User> postResponse(final User user) {
        return ResponseEntity
                .created(URI.create("/users/" + user.getId().toString()))
                .body(user);
    }

    /** Fallback method for Circuit breaker.
     *
     * @param e Exception
     * @return  empty user and HttpStatus
     */
    public Mono<ResponseEntity<User>> userFallback(final Exception e) {
        log.error(e.toString());
        return Mono.just(new
                ResponseEntity<>(new User(), HttpStatus.SERVICE_UNAVAILABLE));
    }

}
