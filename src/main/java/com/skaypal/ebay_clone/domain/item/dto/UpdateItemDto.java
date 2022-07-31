package com.skaypal.ebay_clone.domain.item.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Null;

public class UpdateItemDto {

    @Null
    Integer id;

    @Length(max = 200)
    private String description;

    @Length(max = 45)
    private String name;

    @Length(max = 45)
    private String category;

    public Integer getId() {return id;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public String getCategory() {return  category;}
    public void setId(Integer id) {this.id = id;}
    public void setName(String name) {this.name = name;}
    public void setDescription(String description) {this.description = description;}
    public void setCategory(String category) {this.category = category;}
}
