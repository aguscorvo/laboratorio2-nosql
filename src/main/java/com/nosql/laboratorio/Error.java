package com.nosql.laboratorio;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Error {
    @Id
    private int number;
    private String description;
}
