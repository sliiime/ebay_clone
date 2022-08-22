package com.skaypal.ebay_clone.domain.bid.model;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Date submissionDate;

    Float price;

    @ManyToOne()
    @JoinColumn(name = "item_id")
    Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bidder")
    User bidder;

    public Bid(){}

    public Bid(Integer id ){
        this.id = id;
    }

    public Bid(Integer id,
               Date submissionDate,
               Float price,
               Item item,
               User bidder){

        this.id = id;
        this.submissionDate = submissionDate;
        this.price = price;
        this.item = item;
        this.bidder = bidder;
    }

    public Bid(Date submissionDate,
               Float price,
               Item item,
               User bidder){

        this.submissionDate = submissionDate;
        this.price = price;
        this.item = item;
        this.bidder = bidder;
    }

    public Bid(CreateBidDto createBidDto) {
        this.item = new Item(createBidDto.getItemId());
        this.bidder = new User(createBidDto.getBidderId());
        this.price = createBidDto.getPrice();
        this.submissionDate = createBidDto.getSubmissionDate();
    }

    public Integer getId(){
        return this.id;
    }

    public Float getPrice(){
        return this.price;
    }

    public Date getSubmissionDate(){
        return this.submissionDate;
    }

    public Item getItem(){
        return this.item;
    }
    public User getBidder(){
        return this.bidder;
    }

    public void setId(Integer id){this.id = id;}
    public void setPrice(Float price){this.price = price;}
    public void setSubmissionDate(Date submissionTime){this.submissionDate = submissionTime;}
    public void setItem(Item item){this.item = item;}
    public void setBidder(User bidder){this.bidder = bidder;}

}
