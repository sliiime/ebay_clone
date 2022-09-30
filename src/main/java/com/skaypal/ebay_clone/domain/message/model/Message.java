package com.skaypal.ebay_clone.domain.message.model;

import com.skaypal.ebay_clone.domain.message.dto.CreateMessageDto;
import com.skaypal.ebay_clone.domain.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender")
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver")
    private User receiver;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean seen;

    private Date sentDate;


    public Message(){}

    public Message(Integer id,
                   String body,
                   User sender,
                   User receiver,
                   Boolean seen) {

        this.id = id;
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.seen = seen;

    }


    public Message(String body,
                   User sender,
                   User receiver,
                   Boolean seen) {

        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.seen = seen;

    }

    public Message(Integer id) {
        this.id = id;
    }

    public Message(CreateMessageDto createMessageDto){
        this.sender = new User(createMessageDto.getSenderId());
        this.body = createMessageDto.body();
        this.receiver = new User(createMessageDto.getReceiverId());
        this.seen = false;
        this.sentDate = createMessageDto.getSentDate();
    }

    public Integer getId() {
        return id;
    }

    public Boolean isSeen() {
        return seen;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public String getBody() {
        return body;
    }

    public Date getSentDate(){return this.sentDate;}

    public void setSeen(Boolean read) {
        this.seen = read;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSentDate(Date sentDate){this.sentDate = sentDate;}
}
