package com.nosql.laboratorio.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Error {
    @Id
    private String id;
    private int number;
    private String description;

    public Error(int number, String description) {
        this.number = number;
        this.description = description;
    }
}
