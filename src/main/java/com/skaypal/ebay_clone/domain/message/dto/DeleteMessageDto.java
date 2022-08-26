package com.skaypal.ebay_clone.domain.message.dto;

import javax.validation.constraints.Null;

public class DeleteMessageDto implements ModifyingMessageDto {

    @Null
    Integer id;

    @Null
    Integer senderId;

    public DeleteMessageDto(){}

    public DeleteMessageDto(Integer id, Integer senderId){
        this.senderId = senderId;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
