package com.nosql.laboratorio.dao;

import com.nosql.laboratorio.models.Error;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorRepository
        extends MongoRepository<Error, String> {
}
