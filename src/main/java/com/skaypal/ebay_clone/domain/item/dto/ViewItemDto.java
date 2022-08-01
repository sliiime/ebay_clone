package com.skaypal.ebay_clone.domain.item.dto;

import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.model.Item;

import java.util.Date;

public class ViewItemDto {

    private Integer id;
    private String name;
    private Float currentBestPrice;
    private Float buyPrice;


    private Float minBid;

    private Integer numOfBids;
    private Double latitude;
    private Double longitude;
    private Date startDate;
    private Date endDate;
    private String description;
    private String category;
    private ItemStatusEnum status;

    private Integer sellerId;

    public ViewItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.currentBestPrice = item.getCurrentBestPrice();
        this.buyPrice = item.getBuyPrice();
        this.minBid = item.getMinBid();
        this.numOfBids = item.getNumOfBids();
        this.latitude = item.getLatitude();
        this.longitude = item.getLongitude();
        this.startDate = item.getStartDate();
        this.endDate = item.getEndDate();
        this.description = item.getDescription();
        this.category = item.getCategory();
        this.status = item.getStatus();
        this.sellerId = item.getSeller().getId();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getCurrentBestPrice() {
        return currentBestPrice;
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

    public String getCategory() {
        return category;
    }

    public ItemStatusEnum getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentBestPrice(Float current_best_price) {
        this.currentBestPrice = current_best_price;
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

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }

    public void setSellerId(Integer sellerId){this.sellerId = sellerId;}
}
