package com.nttdata.bootcamp.userservice.infrastructure.repository;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.domain.User;
import com.nttdata.bootcamp.userservice.infrastructure.model.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** User Repository implementation.
 *
 * @author Gustavo Meza
 *
 */
@Component
@RequiredArgsConstructor
public class UserCrudRepository implements UserRepository {

    /**
     * basic crud repository.
     */
    private final IClientCrudRepository repository;

    /** Get user by id.
     *
     * @param id    user id
     * @return      user
     */
    @Override
    public Mono<User> getUserById(final Integer id) {
        return repository.findById(id)
                .map(this::mapUserDaoToUser);
    }

    /** Get All users.
     *
     * @return Flux of users
     */
    @Override
    public Flux<User> getAll() {
        return repository.findAll()
                .map(this::mapUserDaoToUser);
    }

    /** Create new user.
     *
     * @param user  user to be created
     * @return      created user
     */
    @Override
    public Mono<User> createUser(final User user) {
        return repository.save(mapUserToUserDao(user))
                .map(this::mapUserDaoToUser);
    }

    /** Update user.
     *
     * @param id    id of the user to be updated
     * @param user  user with attributes to update
     * @return      Updated user
     */
    @Override
    public Mono<User> updateUser(final Integer id, final User user) {
        user.setId(id);
        return repository.findById(id)
                .thenReturn(mapUserToUserDao(user))
                .flatMap(repository::save)
                .map(this::mapUserDaoToUser);
    }

    /** Delete user.
     *
     * @param id    id of the user to be deleted
     * @return      no content
     */
    @Override
    public Mono<Void> deleteUserById(final Integer id) {
        return repository.deleteById(id);
    }

    private User mapUserDaoToUser(final UserDao userDao) {
        User user = new User();
        BeanUtils.copyProperties(userDao, user);
        return user;
    }

    private UserDao mapUserToUserDao(final User user) {
        UserDao userDao = new UserDao();
        BeanUtils.copyProperties(user, userDao);
        return userDao;
    }

}
