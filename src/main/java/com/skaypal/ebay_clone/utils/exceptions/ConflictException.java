package com.skaypal.ebay_clone.utils.exceptions;

public class ConflictException extends RuntimeException {

    protected String message;

    protected ConflictException(){}

    protected ConflictException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
