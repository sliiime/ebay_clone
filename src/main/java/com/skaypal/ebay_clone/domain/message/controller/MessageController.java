package com.skaypal.ebay_clone.domain.message.controller;


import com.auth0.jwt.JWT;
import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.DeleteMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.UpdateMessageDto;
import com.skaypal.ebay_clone.domain.message.dto.ViewMessageDto;
import com.skaypal.ebay_clone.domain.message.service.MessageService;
import com.skaypal.ebay_clone.utils.Responses;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/message")
public class MessageController {

    private MessageService messageService;
    private JWTUtil jwtUtil;

    private SimpMessagingTemplate simp;
    private final String location = "ebay_clone/api/message";

    @Autowired
    public MessageController(MessageService messageService,
                             JWTUtil jwtUtil) {

        this.messageService = messageService;
        this.jwtUtil = jwtUtil;
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
    public ResponseEntity<?> createMessage(@Valid @RequestBody CreateMessageDto createMessageDto, HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        createMessageDto.setSenderId(jwtUtil.retrieveUserId(token));

        ViewMessageDto message = messageService.createMessage(createMessageDto);

        return Responses.created(String.format("%s/%s", location, message.getId().toString()));
    }


//    @MessageMapping("/chat")
//    public void processMessage(@Payload CreateMessageDto messageDto){
//            messageDto.setSenderId(jwtUtil.retrieveUserId(messageDto.getAuthToken()));
//            ViewMessageDto viewMessageDto = messageService.createMessage(messageDto);
//            simp.convertAndSendToUser(messageDto.getReceiverId().toString(),"/private",viewMessageDto);
//    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateMessage(@Valid @RequestBody UpdateMessageDto updateMessageDto,
                                           @PathVariable Integer id,
                                           HttpServletRequest request){

        String token = request.getHeader("Authorization");


        updateMessageDto.setId(id);
        updateMessageDto.setSenderId(jwtUtil.retrieveUserId(token));

        messageService.updateMessage(updateMessageDto);

        return ResponseEntity.ok().build();

    }

    @GetMapping(path = "/users")
    public ResponseEntity<?> getConversationUsers(@RequestParam Integer page, HttpServletRequest request){

        String token = request.getHeader("Authorization");
        int ofUser = jwtUtil.retrieveUserId(token);

        return ResponseEntity.ok(messageService.getConversationUsers(ofUser,page));

    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Integer id,HttpServletRequest request){

        String token = request.getHeader("Authorization");

        DeleteMessageDto deleteMessageDto = new DeleteMessageDto(id, jwtUtil.retrieveUserId(token));

        messageService.deleteMessage(deleteMessageDto);

        return ResponseEntity.ok().build();
    }
}
