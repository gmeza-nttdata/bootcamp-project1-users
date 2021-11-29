package com.nttdata.bootcamp.userservice.infrastructure.repository;

import com.nttdata.bootcamp.userservice.infrastructure.model.dao.UserDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IClientCrudRepository
        extends ReactiveCrudRepository<UserDao, Integer> {

}
