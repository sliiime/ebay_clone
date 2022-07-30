package com.skaypal.ebay_clone.validator;

import org.springframework.http.HttpStatus;

public class ValidationResult {
    private final String errorMessage;
    private final boolean isValid;


    protected ValidationResult(String errorMessage,boolean isValid){
        this.errorMessage = errorMessage;
        this.isValid = isValid;
    }

    public static ValidationResult valid(){
        return new ValidationResult(null,true);
    }


    public static ValidationResult invalid(String errorMessage){
        return new ValidationResult(errorMessage,false);
    }

    public boolean isValid(){
        return isValid;
    }

    public String getErrorMessage(){return errorMessage;}
}
