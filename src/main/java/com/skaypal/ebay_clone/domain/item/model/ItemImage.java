package com.skaypal.ebay_clone.domain.item.model;


import javax.persistence.*;

@Entity
@Table(name = "item_image")
public class ItemImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "item_id")
    private Item item;


    @Column(name = "content_type")
    private String contentType;


    public ItemImage(){}

    public Integer getId(){return this.id;}

    public String getContentType() {return this.contentType;}

    public Item getItem(){return this.item;}
    public String getName(){return name;}

    public void setId(Integer id){this.id = id;}

    public void setContentType(String contentType){this.contentType = contentType;}
    public void setName(String relativePath){this.name = relativePath;}
    public void setItem(Item item){this.item = item;}
}
