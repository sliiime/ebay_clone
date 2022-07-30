package com.skaypal.ebay_clone.utils.exceptions;

public class ConflictException extends RuntimeException {

    protected String message;

    public ConflictException(){}

    public ConflictException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
