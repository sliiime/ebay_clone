package com.skaypal.ebay_clone.domain.item.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table()
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float current_best_price;
    private Float buy_price;
    private Float min_bid;
    private Integer num_of_bids;
    private Double latitude;
    private Double longitude;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date start_date;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date end_date;
    private String description;
    private String category;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatusEnum status;

    public Item(){}

    public Item(Integer id,
                String name,
                Float current_best_price,
                Float buy_price,
                Float min_bid,
                Integer num_of_bids,
                Double latitude,
                Double longitude,
                Date start_date,
                Date end_date,
                String description,
                String category,
                ItemStatusEnum status) {

        this.id = id;
        this.name = name;
        this.current_best_price = current_best_price;
        this.buy_price = buy_price;
        this.min_bid = min_bid;
        this.num_of_bids = num_of_bids;
        this.latitude = latitude;
        this.longitude = longitude;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public Item(String name,
                Float current_best_price,
                Float buy_price,
                Float min_bid,
                Integer num_of_bids,
                Double latitude,
                Double longitude,
                Date start_date,
                Date end_date,
                String description,
                String category,
                ItemStatusEnum status) {

        this.name = name;
        this.current_best_price = current_best_price;
        this.buy_price = buy_price;
        this.min_bid = min_bid;
        this.num_of_bids = num_of_bids;
        this.latitude = latitude;
        this.longitude = longitude;
        this.start_date = start_date;
        this.end_date = end_date;
        this.description = description;
        this.category = category;
        this.status = status;
    }

    public Integer  getId() { return id; }
    public String   getName() { return name; }
    public Float    getCurrent_best_price() { return current_best_price; }
    public Float    getBuy_price() { return buy_price; }
    public Float    getMin_bid() { return min_bid; }
    public Integer  getNum_of_bids() { return num_of_bids; }
    public Double   getLatitude() { return latitude; }
    public Double   getLongitude() { return longitude; }
    public Date     getStart_date() { return start_date; }
    public Date     getEnd_date() { return end_date; }
    public String   getDescription() { return description; }
    public String   getCategory() { return category; }
    public ItemStatusEnum getStatus() { return status; }

    public void  setId(Integer id) { this.id = id; }
    public void  setName(String name) { this.name = name; }
    public void  setCurrent_best_price(Float current_best_price) { this.current_best_price = current_best_price; }
    public void  setBuy_price(Float buy_price) { this.buy_price = buy_price; }
    public void  setMin_bid(Float min_bid) { this.min_bid = min_bid; }
    public void  setNum_of_bids(Integer num_of_bids) { this.num_of_bids = num_of_bids; }
    public void  setLatitude(Double latitude) { this.latitude = latitude; }
    public void  setLongitude(Double longitude) { this.longitude = longitude; }
    public void  setStart_date(Date start_date) { this.start_date = start_date; }
    public void  setEnd_date(Date end_date) { this.end_date = end_date; }
    public void  setDescription(String description) { this.description = description; }
    public void  setCategory(String category) { this.category = category; }
    public void  setStatus(ItemStatusEnum status) { this.status = status; }
}
