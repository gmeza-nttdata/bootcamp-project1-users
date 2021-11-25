package com.nttdata.bootcamp.userservice.infrastructure.repository;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.domain.User;
import com.nttdata.bootcamp.userservice.infrastructure.model.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class UserCrudRepository implements UserRepository {

    @Autowired
    IClientCrudRepository repository;

    @Override
    public Mono<User> getClientById(Integer id) {
        return repository.findById(id)
                .map(this::mapClientDaoToClient);
    }

    @Override
    public Flux<User> getAll() {
        return repository.findAll()
                .map(this::mapClientDaoToClient);
    }

    @Override
    public Mono<User> createClient(User user) {
        return repository.save(mapClientToClientDao(user))
                .map(this::mapClientDaoToClient);
    }

    @Override
    public Mono<User> updateClient(Integer id, User user) {
        user.setId(id);
        return repository.findById(id).map(c -> repository.save(mapClientToClientDao(user)).subscribe()).thenReturn(user);
    }

    @Override
    public Mono<Void> deleteClientById(Integer id) {
        //log.info("Deleting client: " + id);
        return repository.deleteById(id);
    }

    private User mapClientDaoToClient(UserDao userDao) {
        User user = new User();
        user.setAddress(userDao.getAddress());
        user.setType(userDao.getType());
        user.setFullName(userDao.getFullName());
        user.setBirthDate(userDao.getBirthDate());
        user.setId(userDao.getId());
        return user;
    }

    private UserDao mapClientToClientDao(User user) {
        UserDao userDao = new UserDao();
        userDao.setAddress(user.getAddress());
        userDao.setType(user.getType());
        userDao.setFullName(user.getFullName());
        userDao.setBirthDate(user.getBirthDate());
        userDao.setId(user.getId());
        return userDao;
    }

}
