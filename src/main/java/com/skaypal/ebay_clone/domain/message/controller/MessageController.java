package com.skaypal.ebay_clone.domain.message.controller;


import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.UpdateMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.ViewMessageDto;
import com.skaypal.ebay_clone.domain.message.model.Message;
import com.skaypal.ebay_clone.domain.message.service.MessageService;
import com.skaypal.ebay_clone.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/message")
public class MessageController {

    private MessageService messageService;

    private final String location = "ebay_clone/api/message";

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public ResponseEntity<List<ViewMessageDto>> getMessages() {

        return ResponseEntity.ok(messageService.getMessages());
    }

    @GetMapping(path = "/{id}")
    ResponseEntity<ViewMessageDto> getMessage(@PathVariable Integer id) {
        return ResponseEntity.ok(messageService.getMessage(id));
    }

    @PostMapping
    public ResponseEntity<?> createMessage(@Valid @RequestBody CreateMessageDto createMessageDto) {

        createMessageDto.setSenderId(1); //Should be set by some token probably
        ViewMessageDto message = messageService.createMessage(createMessageDto);

        return Responses.created(String.format("%s/%s", location, message.getId().toString()));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMessage(@Valid @RequestBody UpdateMessageDto updateMessageDto, @PathVariable Integer id){
        updateMessageDto.setId(id);
        messageService.updateMessage(updateMessageDto);
        return ResponseEntity.ok().build();

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer id){
        messageService.deleteMessage(id);

        return ResponseEntity.ok().build();
    }
}
