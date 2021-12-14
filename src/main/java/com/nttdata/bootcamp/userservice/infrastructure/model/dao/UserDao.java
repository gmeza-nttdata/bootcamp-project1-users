package com.nttdata.bootcamp.userservice.infrastructure.model.dao;

import com.nttdata.bootcamp.userservice.domain.User;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

/** User Dao.
 *
 * @author Gustavo Meza
 *
 */
@Data
@Document("user")
public class UserDao {

    /** id of user. */
    private Integer id;
    /** type of user. */
    private User.UserType type;
    /** Full name of user. */
    private String fullName;
    /** address of user. */
    private String address;
    /** Birthdate of user. */
    private LocalDate birthDate;
    /** Email. */
    private String email;
    /** Phone Number. */
    private String phone;
}
