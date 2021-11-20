package com.nttdata.bootcamp.project1.users.infrastructure.rest;

import com.nttdata.bootcamp.project1.users.application.UserOperations;
import com.nttdata.bootcamp.project1.users.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserOperations userOperations;

    @GetMapping
    public Flux<User> get() {
        return userOperations.queryAll();
    }

    @GetMapping("/{id}")
    public Mono<User> get(@PathVariable Integer id) {
        return userOperations.findById(id);
    }

    @PostMapping
    public Mono<User> post(@RequestBody User entity) {
        return userOperations.create(entity);
    }

    @PutMapping("/{id}")
    public Mono<User> put(@PathVariable Integer id, @RequestBody User user) {
        return userOperations.update(id, user);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Integer id) {
        return userOperations.delete(id);
    }

}
