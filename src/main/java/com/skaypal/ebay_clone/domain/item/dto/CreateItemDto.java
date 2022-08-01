package com.skaypal.ebay_clone.domain.item.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
public class CreateItemDto {

    @NotNull
    @Length(max = 45)
    private String name;

    @NotNull
    private Float buyPrice;

    @Length(max = 200)
    private String description;

    @NotNull
    @Length(max = 45)
    private String category;

    public CreateItemDto() {}

    public CreateItemDto(String name,
                         Float buyPrice,
                         String description,
                         String category) {
        this.name = name;
        this.buyPrice = buyPrice;
        this.description = description;
        this.category = category;
    }

    public String getName() {return name;}
    public Float getBuyPrice() {return buyPrice;}
    public String getDescription() {return description;}
    public String getCategory() {return category;}
    public void setName(String name) {this.name = name;}
    public void setBuyPrice(Float buyPrice) {this.buyPrice = buyPrice; }
    public void setDescription(String description) {this.description = description; }
    public void setCategory(String category) {this.category = category; }
}
