package com.skaypal.ebay_clone.domain.message.validator;

import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.message.repository.MessageRepository;
import com.skaypal.ebay_clone.domain.message.validator.steps.ReceiverIdValidation;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageValidator {

    MessageRepository messageRepository;
    UserValidator userValidator;

    ItemValidator itemValidator;

    @Autowired
    public MessageValidator(MessageRepository messageRepository,UserValidator userRepository){
        this.messageRepository = messageRepository;
        this.userValidator = userRepository;
    }

    public ValidationResult validateCreateMessageDto(CreateMessageDto createMessageDto){
        return new ReceiverIdValidation(userValidator).validate(createMessageDto);
    }
    
}