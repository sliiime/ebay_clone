package com.skaypal.ebay_clone.domain.item.dto;



public class ItemImageDto {

    public ItemImageDto(){}

    public ItemImageDto(String name,String content,String contentType){
        this.name = name;
        this.content = content;
        this.contentType = contentType;
    }
    private String content;
    private String contentType;

    private String name;

    public String getContentType(){
        return this.contentType;
    }

    public String getContent(){
        return this.content;
    }

}
