package com.nosql.laboratorio.services;

import com.nosql.laboratorio.dao.ErrorRepository;
import com.nosql.laboratorio.models.ErrorObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ErrorService {

    private final ErrorRepository errorRepository;

    public List<ErrorObject> getAll() {
        return errorRepository.findAll();
    }

    public void create(ErrorObject error){ errorRepository.save(error); }

}
