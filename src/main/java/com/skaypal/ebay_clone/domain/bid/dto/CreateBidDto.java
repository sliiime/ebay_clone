package com.skaypal.ebay_clone.domain.bid.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

public class CreateBidDto {

    @Null
    Integer bidderId;

    @NotNull
    Integer itemId;

    @NotNull
    Float price;

    @Null
    Date submissionDate;


    public CreateBidDto(){}

    public CreateBidDto(Integer bidderId,
                        Integer itemId,
                        Float price){

        this.bidderId = bidderId;
        this.price = price;
        this.itemId = itemId;
    }

    public Integer getBidderId(){
        return bidderId;
    }

    public Date getSubmissionDate(){return this.submissionDate;}
    public Integer getItemId(){
        return this.itemId;
    }
    public Float getPrice(){
        return this.price;
    }

    public void setBidderId(Integer bidderId){this.bidderId = bidderId;}
    public void setItemId(Integer itemId){this.itemId = itemId;}
    public void setPrice(Float price){this.price = price;}

    public void setSubmissionDate(Date submissionDate){this.submissionDate = submissionDate;}
}
