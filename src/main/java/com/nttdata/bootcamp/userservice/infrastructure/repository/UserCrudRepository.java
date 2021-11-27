package com.nttdata.bootcamp.userservice.infrastructure.repository;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.domain.User;
import com.nttdata.bootcamp.userservice.infrastructure.model.dao.UserDao;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
public class UserCrudRepository implements UserRepository {

    @Autowired
    IClientCrudRepository repository;

    @Override
    public Mono<User> getUserById(Integer id) {
        return repository.findById(id)
                .map(this::mapUserDaoToUser);
    }

    @Override
    public Flux<User> getAll() {
        return repository.findAll()
                .map(this::mapUserDaoToUser);
    }

    @Override
    public Mono<User> createUser(User user) {
        return repository.save(mapUserToUserDao(user))
                .map(this::mapUserDaoToUser);
    }

    @Override
    public Mono<User> updateUser(Integer id, User user) {
        user.setId(id);
        return repository.findById(id)
                .thenReturn(mapUserToUserDao(user))
                .flatMap(repository::save)
                .map(this::mapUserDaoToUser);
    }

    @Override
    public Mono<Void> deleteUserById(Integer id) {
        return repository.deleteById(id);
    }

    private User mapUserDaoToUser(UserDao userDao) {
        User user = new User();
        BeanUtils.copyProperties(userDao, user);
        return user;
    }

    private UserDao mapUserToUserDao(User user) {
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(user, userDao);
        return userDao;
    }

}
