package com.skaypal.ebay_clone.domain.bid.dto;

import com.skaypal.ebay_clone.domain.bid.model.Bid;

public class ViewBidDto {
    Integer id;
    Integer bidderId;
    Integer itemId;
    Float price;

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

    public Float price(){
        return this.price;
    }

    public void setId(Integer id){this.id = id;}
    public void setBidderId(Integer id){this.bidderId = bidderId;}
    public void setItemId(Integer id){this.itemId = itemId;}
    public void setPrice(Float price){this.price = price;}

}
