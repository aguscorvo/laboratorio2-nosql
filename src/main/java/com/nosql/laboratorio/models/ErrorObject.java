package com.nosql.laboratorio.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class ErrorObject {
    @Id
    private String id;
    private int error;
    private String description;

    public ErrorObject(int error, String description) {
        this.error = error;
        this.description = description;
    }
}
