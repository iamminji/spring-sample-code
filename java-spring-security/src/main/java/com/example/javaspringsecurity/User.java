package com.example.javaspringsecurity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;


@Data
@Entity
public class User {

    @Id
    private Long id;

    private String username;
    private String password;

}
