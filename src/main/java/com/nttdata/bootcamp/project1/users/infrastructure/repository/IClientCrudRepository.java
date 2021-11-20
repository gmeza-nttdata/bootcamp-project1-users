package com.nttdata.bootcamp.project1.users.infrastructure.repository;

import com.nttdata.bootcamp.project1.users.infrastructure.model.dao.UserDao;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IClientCrudRepository extends ReactiveCrudRepository<UserDao, Integer> {

}
