package com.skaypal.ebay_clone.domain.rating.validator;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.validator.steps.RatedValidator;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;

public class RatingValidator {

    @Autowired
    UserValidator userValidator;

    public ValidationResult validateCreateRatingDto(CreateRatingDto createRatingDto){

        return new RatedValidator(userValidator).validate(createRatingDto);

    }
}
