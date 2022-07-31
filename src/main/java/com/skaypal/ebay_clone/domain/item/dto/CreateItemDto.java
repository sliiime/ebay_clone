package com.skaypal.ebay_clone.domain.item.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class CreateItemDto {

    @NotNull
    @Length(max = 45)
    private String name;

    @NotNull
    private Float buy_price;

    @Length(max = 200)
    private String description;

    @NotNull
    @Length(max = 45)
    private String category;

    public CreateItemDto() {}

    public CreateItemDto(String name,
                         Float buy_price,
                         String description,
                         String category) {
        this.name = name;
        this.buy_price = buy_price;
        this.description = description;
        this.category = category;
    }

    public String getName() {return name;}
    public Float getBuy_price() {return buy_price;}
    public String getDescription() {return description;}
    public String getCategory() {return category;}
    public void setName(String name) {this.name = name;}
    public void setBuy_price(Float buy_price) {this.buy_price = buy_price; }
    public void setDescription(String description) {this.description = description; }
    public void setCategory(String category) {this.category = category; }
}
