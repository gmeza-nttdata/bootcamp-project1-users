package com.nttdata.bootcamp.userservice.infrastructure.mockrepository;

import com.nttdata.bootcamp.userservice.application.repository.UserRepository;
import com.nttdata.bootcamp.userservice.domain.User;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;

/** Mock repository.
 *
 * @author Gustavo Meza
 *
 */
@Component
public class MockUserRepository implements UserRepository {

    /** Mock get user id.
     *
     * @param id    user id
     * @return      mocked user
     */
    @Override
    public Mono<User> getUserById(final Integer id) {
        User user = new User();
        user.setAddress("Evergreen 123");
        user.setBirthDate(LocalDate.MIN);
        user.setFullName("John Doe");
        user.setId(id);
        user.setType(User.UserType.PERSONAL);
        return Mono.justOrEmpty(user);
    }

    /** Mock get all.
     *
     * @return  Mocked Flux of users
     */
    @Override
    public Flux<User> getAll() {
        return Flux.fromIterable(new ArrayList<>());
    }

    /** Mock create user.
     *
     * @param user  user to be created
     * @return      mocked user
     */
    @Override
    public Mono<User> createUser(final User user) {
        user.setId(2);
        return Mono.justOrEmpty(user);
    }

    /** Mock update.
     *
     * @param id    id of the user to be updated
     * @param user  user with attributes to update
     * @return      mocked
     */
    @Override
    public Mono<User> updateUser(final Integer id, final User user) {
        return null;
    }

    /** Delete user.
     *
     * @param id    id of the user to be deleted
     * @return      mocked
     */
    @Override
    public Mono<Void> deleteUserById(final Integer id) {
        return null;
    }
}
