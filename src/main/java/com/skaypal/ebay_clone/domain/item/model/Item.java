package com.skaypal.ebay_clone.domain.item.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
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


    @ManyToMany
    @JoinTable(name = "item_has_category",
            joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name ="category_id",referencedColumnName = "id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Category> categories;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ItemStatusEnum status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "seller")
    private User seller;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Bid> bids;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bought_by")
    private User boughtBy;

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
                List<Category> categories,
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
        this.categories = categories;
        this.status = status;
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
                List<Category> categories,
                ItemStatusEnum status,
                User boughtBy) {

        this.id = id;
        this.name = name;
        this.buyPrice = buyPrice;
        this.minBid = minBid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.categories = categories;
        this.status = status;
        this.boughtBy = boughtBy;
    }

    public Item(String name,
                Float buyPrice,
                Float minBid,
                Double latitude,
                Double longitude,
                Date startDate,
                Date endDate,
                String description,
                List<Category> categories,
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
        this.categories = categories;
        this.status = status;
        this.seller = seller;
    }

    public Item(String name,
                Float buyPrice,
                Float minBid,
                Double latitude,
                Double longitude,
                Date startDate,
                Date endDate,
                String description,
                List<Category> categories,
                ItemStatusEnum status,
                User seller,
                User boughtBy) {

        this.name = name;
        this.buyPrice = buyPrice;
        this.minBid = minBid;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.categories = categories;
        this.status = status;
        this.seller = seller;
        this.boughtBy = boughtBy;
    }


    public Item(CreateItemDto createItemDto) {
        this.name = createItemDto.getName();
        this.buyPrice = createItemDto.getBuyPrice();
        this.description = createItemDto.getDescription();
        this.seller = new User(createItemDto.getOwnerId());
        this.startDate = createItemDto.getStartDate();
        this.minBid = createItemDto.getMinBid() == null ? 0 : createItemDto.getMinBid();
        this.endDate = createItemDto.getEndDate();
        this.status = createItemDto.getStartDate().compareTo(new Date()) <= 0 ? ItemStatusEnum.ONGOING : ItemStatusEnum.PREVIEW;
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

    public User getSeller() {
        return seller;
    }

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

    public List<Category> getCategories() {
        return categories;
    }

    public ItemStatusEnum getStatus() {
        return status;
    }

    public User getBoughtBy() {
        return boughtBy;
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

    public void setCategory(List<Category> categories) {
        this.categories = categories;
    }

    public void setStatus(ItemStatusEnum status) {
        this.status = status;
    }

    public void setBoughtBy(User boughtBy) {
        this.boughtBy = boughtBy;
    }

    public void updateItemWithDto(UpdateItemDto updateItemDto) {
        if (updateItemDto.getName() != null)
            this.name = updateItemDto.getName();
        if (updateItemDto.getDescription() != null)
            this.description = updateItemDto.getDescription();
    }

    public void addCategory(Category category){
        if (categories == null) this.categories = new ArrayList<>();
        this.categories.add(category);
    }

    public boolean hasExpired() {
        return new Date().compareTo(endDate) >= 0;
    }
}
