package com.nttdata.bootcamp.userservice.infrastructure.mockrepository;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;

@Component
public class MockUserRepository implements UserRepository {

    @Override
    public Mono<User> getUserById(Integer id) {
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
    public Mono<User> createUser(User user) {
        user.setId(2);
        return Mono.justOrEmpty(user);
    }

    @Override
    public Mono<User> updateUser(Integer id, User user) {
        return null;
    }

    @Override
    public Mono<Void> deleteUserById(Integer id) {
        return null;
    }
}
