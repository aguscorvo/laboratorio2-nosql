package com.nosql.laboratorio.dao;

import com.nosql.laboratorio.models.ErrorObject;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorRepository
        extends MongoRepository<ErrorObject, String> {
}
