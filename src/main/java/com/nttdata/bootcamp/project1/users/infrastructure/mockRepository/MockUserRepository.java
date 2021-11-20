package com.nttdata.bootcamp.project1.users.infrastructure.mockRepository;

import com.nttdata.bootcamp.project1.users.application.repository.UserRepository;
import com.nttdata.bootcamp.project1.users.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class MockUserRepository implements UserRepository {

    @Override
    public Mono<User> getClientById(Integer id) {
        User user = new User();
        user.setAddress("Evergreen 123");
        user.setBirthDate(LocalDate.MIN);
        user.setFullName("John Doe");
        user.setId(id);
        user.setType(User.UserType.PERSONAL);
        return Mono.justOrEmpty(user);
    }

    @Override
    public Flux<User> getAll() {
        return Flux.fromIterable(new ArrayList<>());
    }

    @Override
    public Mono<User> createClient(User user) {
        user.setId(2);
        return Mono.justOrEmpty(user);
    }

    @Override
    public Mono<User> updateClient(Integer id, User user) {
        return null;
    }

    @Override
    public Mono<Void> deleteClientById(Integer id) {
        return null;
    }
}
