package com.skaypal.ebay_clone.utils.exceptions;

public class NotFoundException extends RuntimeException{

    protected String message;

    public NotFoundException(String message){
        this.message = message;
    }

    public NotFoundException(){}

    @Override
    public String getMessage() {
        return message;
    }
}
