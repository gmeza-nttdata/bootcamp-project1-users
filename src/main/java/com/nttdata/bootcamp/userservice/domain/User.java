package com.nttdata.bootcamp.userservice.domain;

import lombok.Data;
import java.time.LocalDate;

/** User Domain.
 *
 * @author Gustavo Meza
 *
 */
@Data
public class User {

    /** User Type enum.
     *
     */
    public enum UserType { PERSONAL, BUSINESS }

    /** id of user. */
    private Integer id;
    /** type of user. */
    private UserType type;
    /** Full name of user. */
    private String fullName;
    /** address of user. */
    private String address;
    /** Birthdate of user. */
    private LocalDate birthDate;

}
