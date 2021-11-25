package com.nttdata.bootcamp.userservice.application.repository;

import com.nttdata.bootcamp.userservice.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface UserRepository {

    Mono<User> getClientById(Integer id);
    Flux<User> getAll();
    Mono<User>  createClient(User user);
    Mono<User>  updateClient(Integer id, User user);
    Mono<Void> deleteClientById(Integer id);

}
