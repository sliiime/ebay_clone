package com.skaypal.ebay_clone.domain.message.validator;

import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.message.repository.JPAMessageRepository;
import com.skaypal.ebay_clone.domain.message.validator.steps.ReceiverIdValidation;
import com.skaypal.ebay_clone.domain.user.validator.UserValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MessageValidator {

    JPAMessageRepository JPAMessageRepository;
    UserValidator userValidator;

    ItemValidator itemValidator;

    @Autowired
    public MessageValidator(JPAMessageRepository JPAMessageRepository, UserValidator userRepository){
        this.JPAMessageRepository = JPAMessageRepository;
        this.userValidator = userRepository;
    }

    public ValidationResult validateCreateMessageDto(CreateMessageDto createMessageDto){
        return new ReceiverIdValidation(userValidator).validate(createMessageDto);
    }
    
}
