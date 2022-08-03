package com.skaypal.ebay_clone.domain.rating.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;

public class RatingBadRequestException extends BadRequestException {
    public RatingBadRequestException(String errorMessage) {
        this.message = errorMessage;
    }
}
