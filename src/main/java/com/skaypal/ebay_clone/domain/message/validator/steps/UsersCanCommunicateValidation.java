package com.skaypal.ebay_clone.domain.message.validator.steps;

import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersCanCommunicateValidation extends ValidationStep<CreateMessageDto> {

    ItemValidator itemValidator;

    @Autowired
    public UsersCanCommunicateValidation(ItemValidator itemValidator){
        this.itemValidator = itemValidator;
    }

    @Override
    public ValidationResult validate(CreateMessageDto toValidate) {
        return itemValidator.usersHaveMadeTransaction(toValidate.getReceiverId(),toValidate.getSenderId()) ?
                checkNext(toValidate) :
                ValidationResult.invalid("You can't send a message to this user");
    }
}
