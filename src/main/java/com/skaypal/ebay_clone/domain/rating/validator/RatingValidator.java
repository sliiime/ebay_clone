package com.skaypal.ebay_clone.domain.rating.validator;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.validator.steps.RatedByValidator;
import com.skaypal.ebay_clone.domain.rating.validator.steps.RatedValidator;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RatingValidator {

    UserValidator userValidator;


    @Autowired
    public RatingValidator(UserValidator userValidator){
        this.userValidator = userValidator;
    }
    public ValidationResult validateCreateRatingDto(CreateRatingDto createRatingDto){

        return new RatedValidator(userValidator).
                linkWith(new RatedByValidator(userValidator)).
                validate(createRatingDto);

    }
}
