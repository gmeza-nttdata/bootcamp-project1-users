package com.nttdata.bootcamp.project1.users.infrastructure.model.dao;

import com.nttdata.bootcamp.project1.users.domain.User;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document("client")
public class UserDao {

    @Id
    private Integer id;
    private User.UserType type;
    private String fullName;
    private String address;
    private LocalDate birthDate;

}
