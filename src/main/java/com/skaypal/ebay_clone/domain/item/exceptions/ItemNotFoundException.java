package com.skaypal.ebay_clone.domain.item.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class ItemNotFoundException extends NotFoundException {

    public ItemNotFoundException(String fieldName, String fieldValue) {
        this.message = String.format("Item with %s [%s] was not found",fieldName,fieldValue);
    }
}
