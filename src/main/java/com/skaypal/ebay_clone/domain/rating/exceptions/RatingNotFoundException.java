package com.skaypal.ebay_clone.domain.rating.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class RatingNotFoundException extends NotFoundException {

    public RatingNotFoundException(Integer id){
        this.message = String.format("Rating with id [%s] was not found",id.toString());
    }
}
