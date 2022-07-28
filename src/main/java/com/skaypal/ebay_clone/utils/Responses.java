package com.skaypal.ebay_clone.utils;

import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class Responses {

    public static <T> ResponseEntity<T> created(String location){
        return ResponseEntity.created(URI.create(location)).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> methodArgumentNotValidHandler(MethodArgumentNotValidException m){ //Thrown when a constraint is violated by a dto
        List<String> errors = new LinkedList<>();
        for (FieldError error : m.getBindingResult().getFieldErrors()){
            errors.add(error.getField() + " " + error.getDefaultMessage()); //Needs some improving :D
        }

        return ResponseEntity.badRequest().body(errors);
    }

}
