package com.skaypal.ebay_clone.domain.category.exceptions;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class CategoryNotFoundException extends NotFoundException {

    public CategoryNotFoundException(String str) {

        this.message = String.format("Category [%s] not found",str);
    }
}
