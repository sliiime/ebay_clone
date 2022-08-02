package com.skaypal.ebay_clone.domain.message.dto;

import com.skaypal.ebay_clone.domain.message.model.Message;

public class ViewMessageDto {

    Integer id;

    String body;

    Integer senderId;

    Integer receiverId;

    Boolean read;

    public ViewMessageDto(Message message){
        this.id = message.getId();
        this.body = message.getBody();
        this.senderId = message.getSender().getId();
        this.receiverId = message.getReceiver().getId();
        this.read = message.isSeen();
    }

    public ViewMessageDto(){}

    public Integer getId() {return id;}
    public String getBody() {return body;}
    public Integer getSenderId(){return senderId;}
    public Integer getReceiverId(){return receiverId;}
    public Boolean isRead(){return read;}

    public void setId(Integer id){this.id = id;}
    public void setBody(String body){this.body = body;}
    public void setSenderId(Integer senderId){this.senderId = senderId;}
    public void setReceiverId(Integer receiverId){this.receiverId = receiverId;}
    public void setRead(Boolean read){this.read = read;}


}
