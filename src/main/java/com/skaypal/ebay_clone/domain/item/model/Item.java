package com.skaypal.ebay_clone.domain.item.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.user.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table()
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "buy_price")
    private Float buyPrice;

    @Column(name = "min_bid")

    private Float minBid;
    private Double latitude;
    private Double longitude;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date startDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date endDate;
    private String description;
    private String category;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller")
    private User seller;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Bid> bids;

    public Item() {
    }

    public Item(Integer id,
                String name,
                Float buyPrice,
                Float minBid,
                Double latitude,
                Double longitude,
                Date startDate,
                Date endDate,
                String description,
                String category,
                ItemStatusEnum status) {

        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.minBid = minBid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public Item(String name,
                Float buyPrice,
                Float minBid,
                Double latitude,
                Double longitude,
                Date startDate,
                Date endDate,
                String description,
                String category,
                ItemStatusEnum status,
                User seller) {

        this.name = name;
        this.buyPrice = buyPrice;
        this.minBid = minBid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.category = category;
        this.status = status;
        this.seller = seller;
    }

    

    public Item(CreateItemDto createItemDto) {
        this.name = createItemDto.getName();
        this.buyPrice = createItemDto.getBuyPrice();
        this.category = createItemDto.getCategory();
        this.description = createItemDto.getDescription();
    }

    public Item(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getBuyPrice() {
        return buyPrice;
    }

    public Float getMinBid() {
        return minBid;
    }

    public Double getLatitude() {
        return latitude;
    }

    public User getSeller() { return seller;}

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

    public void setBuyPrice(Float buy_price) {
        this.buyPrice = buy_price;
    }

    public void setMinBid(Float min_bid) {
        this.minBid = min_bid;
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

    public void updateItemWithDto(UpdateItemDto updateItemDto) {
        if (updateItemDto.getName() != null)
            this.name = updateItemDto.getName();
        if (updateItemDto.getDescription() != null)
            this.description = updateItemDto.getDescription();
        if (updateItemDto.getCategory() != null)
            this.category = updateItemDto.getCategory();
    }

}
