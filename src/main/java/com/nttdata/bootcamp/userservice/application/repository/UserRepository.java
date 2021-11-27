package com.nttdata.bootcamp.userservice.application.repository;

import com.nttdata.bootcamp.userservice.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface UserRepository {

    Mono<User> getUserById(Integer id);
    Flux<User> getAll();
    Mono<User> createUser(User user);
    Mono<User> updateUser(Integer id, User user);
    Mono<Void> deleteUserById(Integer id);

}
