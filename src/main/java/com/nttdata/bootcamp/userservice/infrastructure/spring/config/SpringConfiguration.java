package com.nttdata.bootcamp.userservice.infrastructure.spring.config;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.infrastructure.repository.IClientCrudRepository;
import com.nttdata.bootcamp.userservice.infrastructure.repository.UserCrudRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Configuration for Spring.
 *
 * @author Gustavo Meza
 *
 */
@Configuration
public class SpringConfiguration {

    /** Configure userRepository bean for UserOperationsImpl class.
     *
     * @param repository    user repository
     * @return              userCrudRepository bean
     */
    @Bean
    public UserRepository userRepository(
            final IClientCrudRepository repository) {
        return new UserCrudRepository(repository);
    }

}
