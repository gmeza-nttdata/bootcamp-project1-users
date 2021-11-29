package com.nttdata.bootcamp.userservice.application.impl;

import com.nttdata.bootcamp.userservice.application.UserOperations;
import com.nttdata.bootcamp.userservice.domain.User;
import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** Implementation of the user operations.
 *
 * @author Gustavo Meza
 *
 */
@Service
@RequiredArgsConstructor
public class UserOperationsImpl implements UserOperations {

    /** Repository. */
    private final UserRepository userRepository;

    /** Query all users.
     *
     * @return Flux of users.
     */
    @Override
    public Flux<User> queryAll() {
        return userRepository.getAll();
    }

    /** Find user by id.
     *
     * @param id    user id
     * @return      Mono of user found
     */
    @Override
    public Mono<User> findById(final Integer id) {
        return userRepository.getUserById(id);
    }

    /** Create a new user.
     *
     * @param user  user entity
     * @return      created user
     */
    @Override
    public Mono<User>  create(final User user) {
        return userRepository.createUser(user);
    }

    /** Update a user.
     *
     * @param id    id of the user to be updated
     * @param user  user with attributes to update
     * @return      updated user
     */
    @Override
    public Mono<User>  update(final Integer id, final User user) {
        return userRepository.updateUser(id, user);
    }

    /** Delete a user.
     *
     * @param id    id of the user to be deleted
     * @return      no content
     */
    @Override
    public Mono<Void> delete(final Integer id) {
        return userRepository.deleteUserById(id);
    }
}
