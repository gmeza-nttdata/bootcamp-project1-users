package com.nttdata.bootcamp.userservice.infrastructure.spring.config;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.infrastructure.repository.UserCrudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean
    public UserRepository userRepository() {
        return new UserCrudRepository();
    }

}
