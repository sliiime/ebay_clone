package com.skaypal.ebay_clone.domain.item.dto;

import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.EndDateValidation;
import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.MinBidBuyPriceValidation;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;

@EndDateValidation(
        startDate = "startDate",
        endDate = "endDate"
)
@MinBidBuyPriceValidation(
        minBid = "minBid",
        buyPrice = "buyPrice"
)
public class CreateItemDto {

    @NotNull
    @Length(max = 45)
    private String name;

    @NotNull
    private Float buyPrice;

    @NotNull Float minBid;

    @Length(max = 200)
    private String description;

    @NotNull
    @Length(max = 45)
    private String category;

    @NotNull
    private Date endDate;

    @Null
    private Date startDate;

    public CreateItemDto() {}

    public CreateItemDto(String name,
                         Float buyPrice,
                         Float minBid,
                         String description,
                         String category,
                         Date endDate,
                         Date startDate) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.description = description;
        this.category = category;
        this.minBid = minBid;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public String getName() {return name;}
    public Float getBuyPrice() {return buyPrice;}
    public String getDescription() {return description;}
    public String getCategory() {return category;}

    public Date getEndDate() { return endDate;}

    public Float getMinBid() { return minBid; }
    public void setName(String name) {this.name = name;}
    public void setBuyPrice(Float buyPrice) {this.buyPrice = buyPrice; }
    public void setDescription(String description) {this.description = description; }
    public void setCategory(String category) {this.category = category; }
    public void setMinBid(Float minBid) {this.minBid = minBid;}
    public void setEndDate(Date endDate){this.endDate = endDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}
}
