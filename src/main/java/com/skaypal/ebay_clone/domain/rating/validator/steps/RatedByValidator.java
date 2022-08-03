package com.skaypal.ebay_clone.domain.rating.validator.steps;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class RatedByValidator extends ValidationStep<CreateRatingDto> {

    UserValidator userValidator;

    public RatedByValidator(UserValidator userValidator){
        this.userValidator = userValidator;
    }

    @Override
    public ValidationResult validate(CreateRatingDto toValidate) {

        return userValidator.userExists(toValidate.getRatedById()) && !toValidate.getRatedId().equals(toValidate.getRatedById()) ?
                checkNext(toValidate) :
                ValidationResult.invalid("You cannot rate yourself");

    }
}
