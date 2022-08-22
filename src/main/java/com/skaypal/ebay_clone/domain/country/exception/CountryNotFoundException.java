package com.skaypal.ebay_clone.domain.country.exception;

import com.skaypal.ebay_clone.utils.exceptions.NotFoundException;

public class CountryNotFoundException extends NotFoundException {

    public CountryNotFoundException(String country){
        this.message = String.format("Country [%s] does not exist",country);
    }
}
