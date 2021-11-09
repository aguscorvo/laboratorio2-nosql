package com.nosql.laboratorio.controllers;

import com.nosql.laboratorio.models.ErrorObject;
import com.nosql.laboratorio.services.ErrorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/errors")
@AllArgsConstructor
public class ErrorController {

    private final ErrorService errorService;

    @GetMapping
    public List<ErrorObject> fetchAllErrors(){
        return errorService.getAll();
    }

}
