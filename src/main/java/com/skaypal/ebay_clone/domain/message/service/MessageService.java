package com.skaypal.ebay_clone.domain.message.service;


import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.DeleteMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.UpdateMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.ViewMessageDto;
import com.skaypal.ebay_clone.domain.message.exceptions.BadRequestMessageException;
import com.skaypal.ebay_clone.domain.message.exceptions.MessageNotFoundException;
import com.skaypal.ebay_clone.domain.message.model.Message;
import com.skaypal.ebay_clone.domain.message.repository.MessageRepository;
import com.skaypal.ebay_clone.domain.message.validator.MessageValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private MessageRepository messageRepository;
    private MessageValidator messageValidator;

    @Autowired
    public MessageService(MessageRepository messageRepository, MessageValidator messageValidator){
        this.messageRepository = messageRepository;
        this.messageValidator = messageValidator;
    }

    public List<ViewMessageDto> getMessages() {
        return messageRepository.findAll().stream().map((m) -> new ViewMessageDto(m)).collect(Collectors.toList());
    }

    public ViewMessageDto getMessage(Integer id){
        return new ViewMessageDto(messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException("id",id.toString())));
    }


    public ViewMessageDto createMessage(CreateMessageDto createMessageDto) {

        ValidationResult validationResult = messageValidator.validateCreateMessageDto(createMessageDto);
        if (!validationResult.isValid()) throw new BadRequestMessageException(validationResult.getErrorMessage());

        Message message = new Message(createMessageDto);

        return new ViewMessageDto(this.messageRepository.save(message));
    }


    public void updateMessage(UpdateMessageDto updateMessageDto) {

        ValidationResult validationResult = messageValidator.validateModifyingMessageDto(updateMessageDto);
        if(!validationResult.isValid()) throw new BadRequestMessageException(validationResult.getErrorMessage());

       Message message = messageRepository.findById(updateMessageDto.getId()).orElseThrow(() -> new MessageNotFoundException(String.format("Message with id [%s] was not found",updateMessageDto.getId().toString())));
       message.setBody(updateMessageDto.getBody());
       messageRepository.save(message);
    }

    public void deleteMessage(DeleteMessageDto deleteMessageDto) {

        ValidationResult validationResult = messageValidator.validateModifyingMessageDto(deleteMessageDto);

        if (!validationResult.isValid()) throw new BadRequestMessageException(validationResult.getErrorMessage());

        messageRepository.findById(deleteMessageDto.getId()).orElseThrow(() -> new MessageNotFoundException(String.format("Message with id [%s] was not found",deleteMessageDto.getId())));

        messageRepository.deleteById(deleteMessageDto.getId());
    }
}
