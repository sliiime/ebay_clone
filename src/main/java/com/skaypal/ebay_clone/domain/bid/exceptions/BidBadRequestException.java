package com.skaypal.ebay_clone.domain.bid.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;

public class BidBadRequestException extends BadRequestException {

    public BidBadRequestException(String message){
        this.message = message;
    }
}
