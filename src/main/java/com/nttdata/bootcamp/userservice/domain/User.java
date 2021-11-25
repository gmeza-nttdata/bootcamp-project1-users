package com.nttdata.bootcamp.userservice.domain;

import lombok.Data;
import java.time.LocalDate;

@Data
public class User {

    public enum UserType {PERSONAL, BUSINESS}

    private Integer id;
    private UserType type;
    private String fullName;
    private String address;
    private LocalDate birthDate;

}
