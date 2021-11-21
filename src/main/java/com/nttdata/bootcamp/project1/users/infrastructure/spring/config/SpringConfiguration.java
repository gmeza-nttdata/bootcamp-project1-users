package com.nttdata.bootcamp.project1.users.infrastructure.spring.config;

import com.nttdata.bootcamp.project1.users.application.repository.UserRepository;
import com.nttdata.bootcamp.project1.users.infrastructure.repository.UserCrudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new UserCrudRepository();
    }

}
