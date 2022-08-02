package com.skaypal.ebay_clone.domain.message.dto;

import net.bytebuddy.build.ToStringPlugin;
import org.hibernate.sql.Update;

import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class UpdateMessageDto {

    @Null
    Integer id;

    @Size(min=1,max = 200)
    String body;

    public UpdateMessageDto(){}

    public Integer getId(){ return this.id;}
    public String getBody(){return this.body;}

    public void setId(Integer id) {this.id = id;}
    public void setBody(String body) {this.body = body;}
}
