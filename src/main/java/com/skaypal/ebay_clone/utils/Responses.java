package com.skaypal.ebay_clone.utils;

import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;
import com.skaypal.ebay_clone.utils.exceptions.ConflictException;
import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

@ControllerAdvice
public class Responses {

    public static <T> ResponseEntity<T> created(String location) {
        return ResponseEntity.created(URI.create(location)).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<String>> methodArgumentNotValidHandler(MethodArgumentNotValidException m) { //Thrown when a constraint is violated by a dto
        List<String> errors = new LinkedList<>();
        for (FieldError error : m.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + " " + error.getDefaultMessage()); //Needs some improving :D
        }

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundExceptionHandler(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<String> conflictExceptionHandler(ConflictException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> badRequestExceptionHandler(BadRequestException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }


}
