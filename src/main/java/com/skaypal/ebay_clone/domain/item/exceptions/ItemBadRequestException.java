package com.skaypal.ebay_clone.domain.item.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.BadRequestException;

public class ItemBadRequestException extends BadRequestException {
    public ItemBadRequestException(String errorMessage) {
        this.message = errorMessage;
    }
}
