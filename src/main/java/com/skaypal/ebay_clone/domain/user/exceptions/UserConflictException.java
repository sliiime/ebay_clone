package com.skaypal.ebay_clone.domain.user.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.ConflictException;

public class UserConflictException extends ConflictException {


    public UserConflictException(String fieldName,String fieldValue){
        this.message = String.format("User with %s [%s] already exists",fieldName,fieldValue);
    }

    public UserConflictException(String message){
        this.message = message;
    }
}
