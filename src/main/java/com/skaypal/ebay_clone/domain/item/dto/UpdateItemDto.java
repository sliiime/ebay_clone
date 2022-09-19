package com.skaypal.ebay_clone.domain.item.dto;

import com.skaypal.ebay_clone.domain.item.model.ItemFields;
import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.EndDateValidation;
import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.MinBidBuyPriceValidation;
import com.skaypal.ebay_clone.domain.item.validator.controllerValidators.UpdateItemDtoValidation;
import com.skaypal.ebay_clone.utils.geo.LatLongMapped;
import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Date;
import java.util.List;

@MinBidBuyPriceValidation
@EndDateValidation
@UpdateItemDtoValidation
public class UpdateItemDto implements ValidatableItemDto, LatLongMapped {

    Integer id;

    @NotNull
    private List<ItemFields> toUpdate;

    @Length(max = 45)
    @Nullable
    private String name;

    @Nullable
    private Float buyPrice;

    @Nullable
    private Float minBid;


    @Nullable
    @Length(max = 200)
    private String description;


    @Nullable
    private List<String> categories;


    @Nullable
    private Date endDate;


    @Nullable
    private Date startDate;

    @Nullable
    private Double longitude;

    @Nullable
    private Double latitude;


    @Nullable
    private List<MultipartFile> images;


    public Integer getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public List<String> getCategories() {return  categories;}

    public Float getBuyPrice(){return this.buyPrice;}

    public Float getMinBid(){return this.minBid;}

    public Date getStartDate(){return this.startDate;}

    public Date getEndDate(){return this.endDate;}

    public Double getLongitude() {return this.longitude;}
    public Double getLatitude(){return this.latitude;}

    public List<MultipartFile> getImages(){return this.images;}
    public List<ItemFields> getToUpdate() {return toUpdate;}


    public void setId(Integer id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setCategories(List<String> categories) {this.categories = categories;}

    public void setImages(List<MultipartFile> images){this.images = images;}

    public void setBuyPrice(Float buyPrice){this.buyPrice = buyPrice;}

    public void setMinBid(Float minBid){this.minBid = minBid;}

    public void setToUpdate(List<ItemFields> toUpdate){this.toUpdate = toUpdate;}

    public void setEndDate(Date endDate){this.endDate = endDate;}
    public void setStartDate(Date startDate){this.startDate = startDate;}
    public void setLongitude(Double longitude){this.longitude = longitude;}
    public void setLatitude(Double latitude){this.latitude = latitude;}

}
