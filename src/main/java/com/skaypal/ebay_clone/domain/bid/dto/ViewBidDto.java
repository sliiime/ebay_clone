package com.skaypal.ebay_clone.domain.bid.dto;

import com.skaypal.ebay_clone.domain.bid.model.Bid;

import java.util.Date;

public class ViewBidDto {
    private Integer id;
    private Integer bidderId;
    private Integer itemId;
    private Float price;

    private Date submissionDate;

    public ViewBidDto(){}

    public ViewBidDto(Integer id,
                      Integer bidderId,
                      Integer itemId,
                      Float price){

        this.id = id;
        this.bidderId = bidderId;
        this.itemId = itemId;
        this.price = price;
    }

    public ViewBidDto(Bid bid) {
        this.id = bid.getId();
        this.bidderId = bid.getBidder().getId();
        this.itemId = bid.getItem().getId();
        this.price = bid.getPrice();
        this.submissionDate = bid.getSubmissionDate();
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getBidderId(){
        return this.bidderId;
    }

    public Integer getItemId(){
        return this.itemId;
    }

    public Float getPrice(){
        return this.price;
    }

    public Date getSubmissionDate(){return this.submissionDate;}

    public void setId(Integer id){this.id = id;}
    public void setBidderId(Integer id){this.bidderId = bidderId;}
    public void setItemId(Integer id){this.itemId = itemId;}
    public void setPrice(Float price){this.price = price;}

    public void setSubmissionDate(Date submissionDate){this.submissionDate = submissionDate;}
}
