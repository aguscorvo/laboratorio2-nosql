package com.nosql.laboratorio.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class ErrorObject {
    @Id
    private String id;
    private int error;
    private String description;
    private LocalDateTime created;

    public ErrorObject(int error, String description, LocalDateTime created) {
        this.error = error;
        this.description = description;
        this.created = created;
    }
}
