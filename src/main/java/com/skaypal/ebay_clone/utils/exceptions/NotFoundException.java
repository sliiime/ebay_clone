package com.skaypal.ebay_clone.utils.exceptions;

public class NotFoundException extends RuntimeException{

    protected String message;

    protected NotFoundException(String message){
        this.message = message;
    }

    protected NotFoundException(){}

    @Override
    public String getMessage() {
        return message;
    }
}
