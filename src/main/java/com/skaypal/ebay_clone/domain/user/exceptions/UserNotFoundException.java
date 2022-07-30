package com.skaypal.ebay_clone.domain.user.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class UserNotFoundException extends NotFoundException {


    public UserNotFoundException(String fieldName,String fieldValue){
        this.message =  String.format("User with %s [%s] was not found",fieldName,fieldName);

    }

    public UserNotFoundException(String message){
        this.message = message;
    }



}
