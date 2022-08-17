package com.skaypal.ebay_clone.domain.message.validator.steps;

import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

public class ReceiverIdValidation extends ValidationStep<CreateMessageDto> {

    private UserValidator userValidator;

    public ReceiverIdValidation(UserValidator userValidator){
        this.userValidator = userValidator;

    }

    @Override
    public ValidationResult validate(CreateMessageDto toValidate) {
        return userValidator.userExists(toValidate.getReceiverId()) ?
                checkNext(toValidate) :
                ValidationResult.invalid("Receiver with this id does not exist");
    }
}
