package com.skaypal.ebay_clone.domain.item.dto;

import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.model.Item;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ViewItemDto {

    private Integer id;
    private String name;
    private Float bestBid;
    private Float buyPrice;
    private Float minBid;
    private Integer numOfBids;
    private Double latitude;
    private Double longitude;
    private Date startDate;
    private Date endDate;
    private String description;

    private List<String> categories;

    private ItemStatusEnum status;
    private Integer sellerId;

    private Integer highestBidderId;

    private Integer boughtById;

    private List<ItemImageDto> images;


    public ViewItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.buyPrice = item.getBuyPrice();
        this.minBid = item.getMinBid();
        this.latitude = item.getLatitude();
        this.longitude = item.getLongitude();
        this.startDate = item.getStartDate();
        this.endDate = item.getEndDate();
        this.description = item.getDescription();
        this.categories = item.getCategories().stream().map(c -> c.getCategory()).collect(Collectors.toList());
        this.status = item.getStatus();
        this.sellerId = item.getSeller().getId();
        this.boughtById = item.getBoughtBy() == null ? null : item.getBoughtBy().getId();
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getBestBid() {
        return bestBid;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public Float getMinBid() {
        return minBid;
    }

    public Integer getNumOfBids() {
        return numOfBids;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Integer getHighestBidderId() {return this.highestBidderId;}
    public Integer getSellerId(){ return sellerId;}

    public Double getLongitude() {
        return longitude;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCategory() {
        return categories;
    }

    public ItemStatusEnum getStatus() {
        return status;
    }

    public Integer getBoughtById(){return boughtById;}

    public List<ItemImageDto> getImages(){return this.images;}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBestBid(Float current_best_price) {
        this.bestBid = current_best_price;
    }

    public void setBuyPrice(Float buy_price) {
        this.buyPrice = buy_price;
    }

    public void setMinBid(Float min_bid) {
        this.minBid = min_bid;
    }

    public void setNumOfBids(Integer num_of_bids) {
        this.numOfBids = num_of_bids;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setStartDate(Date start_date) {
        this.startDate = start_date;
    }

    public void setEndDate(Date end_date) {
        this.endDate = end_date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(List<String> categories) {
        this.categories = categories;
    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }

    public void setHighestBidderId(Integer id){this.highestBidderId = id;}
    public void setSellerId(Integer sellerId){this.sellerId = sellerId;}

    public void setBoughtById(Integer boughtById){this.boughtById = boughtById;}

    public void setImages(List<ItemImageDto> images){
        this.images = images;
    }
}
