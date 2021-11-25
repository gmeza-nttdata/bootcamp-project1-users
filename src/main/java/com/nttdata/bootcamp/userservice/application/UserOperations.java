package com.nttdata.bootcamp.userservice.application;

import com.nttdata.bootcamp.userservice.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserOperations {

    Flux<User> queryAll();
    Mono<User> findById(Integer id);
    Mono<User> create(User user);
    Mono<User>  update(Integer string, User user);
    Mono<Void>   delete(Integer id);



}
