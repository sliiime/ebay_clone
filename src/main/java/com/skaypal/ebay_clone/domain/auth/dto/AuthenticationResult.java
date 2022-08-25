package com.skaypal.ebay_clone.domain.auth.dto;

import com.skaypal.ebay_clone.utils.validator.ServiceResultStatus;

import java.util.Optional;

public class AuthenticationResult<T> {
    private ServiceResultStatus serviceResultStatus = ServiceResultStatus.OK;
    private T t;

    public AuthenticationResult(ServiceResultStatus serviceResultStatus){
        this.serviceResultStatus = serviceResultStatus;
    }

    public AuthenticationResult(T t){
        this.t = t;
    }

    public T hopefully(){
        return this.t;
    }

    public static <T> AuthenticationResult<T> of(ServiceResultStatus serviceResultStatus){
        return new AuthenticationResult<>(serviceResultStatus);
    }

    public static <T> AuthenticationResult<T> of(T t){
        return new AuthenticationResult<>(t);
    }

    public ServiceResultStatus getServiceResultStatus(){
        return this.serviceResultStatus;
    }



}
