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
import com.skaypal.ebay_clone.domain.user.dto.ViewUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private static final int CONVERSATION_USERS_PAGE_SIZE = 10;
    private static final int CONVERSATION_MESSAGES_PAGE_SIZE = 8;
    private final MessageRepository messageRepository;

    private final UserRepository userRepository;
    private final MessageValidator messageValidator;

    @Autowired
    public MessageService(MessageRepository messageRepository,UserRepository userRepository, MessageValidator messageValidator){
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
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

    public Page<ViewUserDto> getConversationUsers(int idOfUser, int page) {

        User ofUser = new User(idOfUser);
        //Check if any auctioned items that "ofUser" has bid on have been bought by timeout and change their status to bought
        Page<User> transactionParticipantsPage = userRepository.getTransactionParticipants(ofUser,PageRequest.of(page,CONVERSATION_USERS_PAGE_SIZE));
        Page<ViewUserDto> transactionParticipantsDtoPage = transactionParticipantsPage.map(ViewUserDto::new);
        return transactionParticipantsDtoPage;
    }

    public Page<ViewMessageDto> getConversationMessages(int ofUser, int withUser,int page) {
        User user1 = new User(ofUser);
        User user2 = new User(withUser);

        Page<Message> conversationMessagesPage = messageRepository.getConversationMessages(user1,user2,PageRequest.of(page,CONVERSATION_MESSAGES_PAGE_SIZE));
        Page<ViewMessageDto> conversationMessagesDtoPage = conversationMessagesPage.map(ViewMessageDto::new);

        return conversationMessagesDtoPage;
    }
}
