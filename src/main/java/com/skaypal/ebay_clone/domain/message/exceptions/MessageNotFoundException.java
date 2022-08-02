package com.skaypal.ebay_clone.domain.message.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class MessageNotFoundException extends NotFoundException {

    public MessageNotFoundException(String fieldName,String fieldValue){
        this.message = String.format("Message with %s [%s] was not found",fieldName,fieldValue);
    }

    public MessageNotFoundException(String message){
        this.message = message;
    }
}
