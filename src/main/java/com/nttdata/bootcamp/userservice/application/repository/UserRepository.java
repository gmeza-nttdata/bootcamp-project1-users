package com.nttdata.bootcamp.userservice.application.repository;

import com.nttdata.bootcamp.userservice.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** User Repository interface.
 *
 * @author Gustavo Meza
 *
 */
@Component
public interface UserRepository {

    /** Get user by its id.
     *
     * @param id    user id
     * @return      Mono of user
     */
    Mono<User> getUserById(Integer id);

    /** Get all users.
     *
     * @return  Flux of users
     */
    Flux<User> getAll();

    /** Create new user.
     *
     * @param user  user to be created
     * @return      created user
     */
    Mono<User> createUser(User user);

    /** Update a user.
     *
     * @param id    id of the user to be updated
     * @param user  user with attributes to update
     * @return      updated user
     */
    Mono<User> updateUser(Integer id, User user);

    /** Delete user.
     *
     * @param id    id of the user to be deleted
     * @return      no content
     */
    Mono<Void> deleteUserById(Integer id);

}
