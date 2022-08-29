package com.skaypal.ebay_clone.domain.item.dto;

import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.EndDateValidation;
import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.MinBidBuyPriceValidation;
import org.hibernate.validator.constraints.Length;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;


@MinBidBuyPriceValidation
@EndDateValidation
public class CreateItemDto {

    @NotNull
    @Length(max = 45)
    private String name;

    @NotNull
    private Float buyPrice;

    @NotNull
    Float minBid;

    @Length(max = 200)
    private String description;

    @NotNull
    @Length(max = 45)
    private List<String> categories;

    @NotNull
    private Date endDate;

    private Date startDate;

    @Null
    Integer ownerId;

    public CreateItemDto() {}

    public CreateItemDto(String name,
                         Float buyPrice,
                         Float minBid,
                         String description,
                         List<String> categories,
                         Date endDate,
                         Date startDate) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.description = description;
        this.categories = categories;
        this.minBid = minBid;
        this.endDate = endDate;
        this.startDate = startDate;
    }

    public CreateItemDto(String name,
                         Float buyPrice,
                         Float minBid,
                         String description,
                         List<String> categories,
                         Date endDate,
                         Date startDate,
                         Integer ownerId) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.description = description;
        this.categories = categories;
        this.minBid = minBid;
        this.endDate = endDate;
        this.startDate = startDate;
        this.ownerId = ownerId;
    }

    public String getName() {return name;}
    public Float getBuyPrice() {return buyPrice;}
    public String getDescription() {return description;}
    public List<String> getCategories() {return categories;}

    public Date getEndDate() { return endDate;}

    public Float getMinBid() { return minBid; }

    public Integer getOwnerId(){return ownerId;}

    public Date getStartDate(){return startDate;}
    public void setName(String name) {this.name = name;}
    public void setBuyPrice(Float buyPrice) {this.buyPrice = buyPrice; }
    public void setDescription(String description) {this.description = description; }
    public void setCategory(List<String> categories) {this.categories = categories; }
    public void setMinBid(Float minBid) {this.minBid = minBid;}
    public void setEndDate(Date endDate){this.endDate = endDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}
    public void setOwnerId(Integer ownerId){this.ownerId = ownerId;}
}
