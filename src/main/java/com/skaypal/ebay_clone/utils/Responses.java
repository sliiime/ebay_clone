package com.skaypal.ebay_clone.utils;

import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.sql.SQLIntegrityConstraintViolationException;

public class Responses {

    public static <T> ResponseEntity<T> created(String location){
        return ResponseEntity.created(URI.create(location)).build();
    }

    public static ResponseStatusException constraintViolation(SQLIntegrityConstraintViolationException x){
        String message = x.getMessage();
        message = message.replaceAll("[^'.']","");
        String[] vea = message.split("['.']");

        String value = vea[0].replaceAll("'","");
        String[] ea = vea[1].replace("_UNIQUE","'").split("'.'");

        String entity = ea[0].replaceAll("'","");
        String attribute = ea[1].replaceAll("'","");

        return new ResponseStatusException(HttpStatus.NOT_FOUND,entity + "with " + attribute + " = " + value + " already exists");

    }

}
