package com.nttdata.bootcamp.userservice.application;

import com.nttdata.bootcamp.userservice.domain.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** User Operations interface.
 *
 * @author Gustavo Meza
 *
 */
public interface UserOperations {

    /** Get all users.
     *
     * @return Flux of users
     */
    Flux<User> queryAll();

    /** Get user by its id.
     *
     * @param id    user id
     * @return      Mono of user
     */
    Mono<User> findById(Integer id);

    /** Create new user.
     *
     * @param user  user to be created
     * @return      created user
     */
    Mono<User> create(User user);

    /** Update a user.
     *
     * @param id    id of the user to be updated
     * @param user  user with attributes to update
     * @return      updated user
     */
    Mono<User>  update(Integer id, User user);

    /** Delete user.
     *
     * @param id    id of the user to be deleted
     * @return      no content
     */
    Mono<Void>   delete(Integer id);



}
