package com.skaypal.ebay_clone.utils.exceptions;

public class BadRequestException extends RuntimeException {

    protected String message;

    public BadRequestException(){}

    public BadRequestException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
