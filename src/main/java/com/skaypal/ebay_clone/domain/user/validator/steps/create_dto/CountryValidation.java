package com.skaypal.ebay_clone.domain.user.validator.steps.create_dto;


import com.skaypal.ebay_clone.domain.country.validator.CountryValidator;
import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;


public class CountryValidation extends ValidationStep<CreateUserDto> {

    CountryValidator countryValidator;

    public CountryValidation(CountryValidator countryValidator){
        this.countryValidator = countryValidator;
    }

    public ValidationResult validate(CreateUserDto toValidate){

        return countryValidator.countryExists(toValidate.getCountry()) ?
                checkNext(toValidate):
                ValidationResult.invalid(String.format("Country [%s] does not exist",toValidate.getCountry()));

    }
}
