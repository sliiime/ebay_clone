package com.skaypal.ebay_clone.domain.rating.validator.steps;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class RatedValidator extends ValidationStep<CreateRatingDto> {

    UserValidator userValidator;
    public RatedValidator(UserValidator userValidator) {
        this.userValidator = userValidator;
    }

    @Override
    public ValidationResult validate(CreateRatingDto toValidate) {
        return userValidator.userExists(toValidate.getRatedId()) ?
                checkNext(toValidate) :
                ValidationResult.invalid(String.format("User with id [%s] does not exist",toValidate.getRatedId()));
    }
}
