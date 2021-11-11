package com.nosql.laboratorio.exceptions;

import com.nosql.laboratorio.models.ErrorObject;
import com.nosql.laboratorio.services.ErrorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@AllArgsConstructor
@ControllerAdvice
public class GlobalException {

    private final ErrorService errorService;

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleAlreadyExistsException (AlreadyExistsException ex) {
        ErrorObject eObject = new ErrorObject(101, ex.getMessage(), LocalDateTime.now());
        errorService.create(eObject);
        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleInvalidPasswordException (InvalidPasswordException ex) {
        ErrorObject eObject = new ErrorObject(104, ex.getMessage(), LocalDateTime.now());
        errorService.create(eObject);
        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleRoleNotFoundException (RoleNotFoundException ex) {
        ErrorObject eObject = new ErrorObject(103, ex.getMessage(), LocalDateTime.now());
        errorService.create(eObject);
        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorObject> handleUserNotFoundException (UserNotFoundException ex) {
        ErrorObject eObject = new ErrorObject(102, ex.getMessage(), LocalDateTime.now());
        errorService.create(eObject);
        return new ResponseEntity<ErrorObject>(eObject, HttpStatus.NOT_FOUND);
    }

}
