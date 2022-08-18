package com.skaypal.ebay_clone.domain.bid.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class BidNotFoundException extends NotFoundException {
    public BidNotFoundException(String message){
        this.message = message;
    }

    public BidNotFoundException(String attribute,String value){
        this.message = String.format("Bid with %s [%s] was not found",attribute,value);
    }


}
