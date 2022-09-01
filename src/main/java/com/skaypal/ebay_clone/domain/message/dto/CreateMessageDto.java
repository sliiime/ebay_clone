package com.skaypal.ebay_clone.domain.message.dto;

import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class CreateMessageDto {

    @Size(min = 1,max = 200)
    String body;

    @NotNull
    Integer receiverId;

    @Null
    Integer senderId;

    @Nullable
    String authToken;


    public CreateMessageDto(){}

    public String body(){return body;}
    public Integer getReceiverId(){return receiverId;}
    public Integer getSenderId(){return senderId;}

    public String getAuthToken(){return this.authToken;}
    public void setBody(String body){this.body = body;}
    public void setReceiverId(Integer receiverId){this.receiverId = receiverId;}
    public void setSenderId(Integer senderId){this.senderId = senderId;}

    public void setAuthToken(String authToken){this.authToken = authToken;}



}
