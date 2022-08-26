package com.skaypal.ebay_clone.domain.message.validator.steps;

import com.skaypal.ebay_clone.domain.message.dto.ModifyingMessageDto;
import com.skaypal.ebay_clone.domain.message.model.Message;
import com.skaypal.ebay_clone.domain.message.repository.MessageRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

import java.util.Optional;

public class IsSenderOfMessageValidation<T extends ModifyingMessageDto> extends ValidationStep<T> {

    MessageRepository messageRepository;
    public IsSenderOfMessageValidation(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public ValidationResult validate(T toValidate){
        Optional<Message> message = messageRepository.findById(toValidate.getId());


        if (message.isEmpty()) return ValidationResult.invalid(String.format("Message with id [%s] was not found",toValidate.getId()));

        Message msg = message.get();

        return msg.getSender().getId() == toValidate.getSenderId() ?
                checkNext(toValidate) :
                ValidationResult.invalid("You cannot delete this message");

    }
}
