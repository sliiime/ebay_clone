package com.skaypal.ebay_clone.domain.message.dto;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class UpdateMessageDto implements ModifyingMessageDto {

    @Null
    Integer id;

    @Null
    Integer senderId;

    @Size(min=1,max = 200)
    String body;

    public UpdateMessageDto(){}

    public Integer getId(){ return this.id;}
    public String getBody(){return this.body;}

    public Integer getSenderId(){return this.senderId;}

    public void setId(Integer id) {this.id = id;}
    public void setBody(String body) {this.body = body;}

    public void setSenderId(Integer senderId){this.senderId = senderId;}
}
