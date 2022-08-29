package com.skaypal.ebay_clone.domain.category.model;

import javax.persistence.*;

@Entity
@Table
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String category;

    public Category(){}

    public Category(Integer id,String category){
        this.id = id;
        this.category = category;
    }

    public Category(int id) {
        this.id = id;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Integer getId(){
        return id;
    }

    public String getCategory(){
        return category;
    }


}
