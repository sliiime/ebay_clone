package com.skaypal.ebay_clone.domain.message.validator.steps;

import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.validator.ValidationResult;
import com.skaypal.ebay_clone.validator.ValidationStep;
import org.springframework.beans.factory.annotation.Autowired;

public class ReceiverIdValidation extends ValidationStep<CreateMessageDto> {

    UserValidator userValidator;
    public ReceiverIdValidation(UserValidator userValidator){
        this.userValidator = userValidator;
    }

    @Override
    public ValidationResult validate(CreateMessageDto toValidate) {
       return userValidator.userExists(toValidate.getReceiverId())
                ? checkNext(toValidate):
                ValidationResult.invalid(String.format("Receiver with id [%s] does not exist",toValidate.getReceiverId().toString()));
    }
}
