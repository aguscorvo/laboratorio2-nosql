package com.nosql.laboratorio.services;

import com.nosql.laboratorio.dao.ErrorRepository;
import com.nosql.laboratorio.models.Error;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    public List<Error> getAll() {
        return errorRepository.findAll();
    }

    public void create(Error error){ errorRepository.save(error); }

}
