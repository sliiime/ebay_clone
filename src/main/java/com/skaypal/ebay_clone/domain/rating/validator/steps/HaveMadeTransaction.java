package com.skaypal.ebay_clone.domain.rating.validator.steps;

import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class HaveMadeTransaction extends ValidationStep<CreateRatingDto> {

    ItemValidator itemValidator;
    public HaveMadeTransaction(ItemValidator itemValidator) {
        this.itemValidator = itemValidator;
    }

    @Override
    public ValidationResult validate(CreateRatingDto toValidate){
        return itemValidator.usersHaveMadeTransaction(toValidate.getRatedId(), toValidate.getRatedById()) ?
                checkNext(toValidate) :
                ValidationResult.invalid("You have not made a transaction yet with this user");
    }
}
