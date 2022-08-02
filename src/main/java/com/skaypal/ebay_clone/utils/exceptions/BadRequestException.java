package com.skaypal.ebay_clone.utils.exceptions;

public class BadRequestException extends RuntimeException {

    protected String message;

    protected BadRequestException(){}

    protected BadRequestException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }

}
