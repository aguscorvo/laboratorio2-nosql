package com.nosql.laboratorio.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class User {
    @Id
    private String id;
    @Indexed(unique=true)
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;

    public User(String email,
                String password,
                String firstName,
                String lastName,
                List<String> roles) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roles = roles;
    }
}
