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
import java.util.stream.Stream;

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

    public static ResponseStatusException notFound(String entity,String fieldName,String fieldValue){
        return new ResponseStatusException(HttpStatus.NOT_FOUND,entity + " with " + fieldName + " " + fieldValue + " not found");
    }
    
    public static ResponseStatusException conflict(String entity,List<String> conflicts){

        String conflictFields = "";
        for (String conflict : conflicts) conflictFields += conflict + " ,";
        conflictFields = conflictFields.substring(0,conflictFields.length()-2);

        return new ResponseStatusException(HttpStatus.CONFLICT,"There already exists a " +entity +" with this : " + conflictFields);

    }

}
