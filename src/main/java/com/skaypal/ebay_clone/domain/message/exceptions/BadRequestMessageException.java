package com.skaypal.ebay_clone.domain.message.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;

public class BadRequestMessageException extends BadRequestException {

    public BadRequestMessageException(String message){
        this.message = message;
    }

}
