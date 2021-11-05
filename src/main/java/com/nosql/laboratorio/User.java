package com.nosql.laboratorio;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Document
public class User {
    @Id
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private List<String> roles;
    private ZonedDateTime created;
}
