package com.skaypal.ebay_clone.domain.rating.dto;

import com.skaypal.ebay_clone.domain.rating.model.Rating;

import java.util.function.Supplier;

public class ViewRatingDto  {

    private Integer id;
    private Integer rating;

    private Integer ratedId;

    private Integer ratedById;

    public ViewRatingDto(){}
    public ViewRatingDto(Rating rating){
        this.id = rating.getRating();
        this.rating = rating.getRating();
        this.ratedId = rating.getRated().getId();
        this.ratedById = rating.getRatedBy().getId();
    }

    public ViewRatingDto(Integer id,Integer ratedById,Integer ratedId,Integer rating){
            this.id = 0;
            this.ratedById = ratedById;
            this.ratedId = ratedId;
            this.rating = rating;
    }

    public Integer getId(){return id;}
    public Integer getRating(){return rating;}
    public Integer getRatedId(){return ratedId;}
    public Integer getRatedById(){return ratedById;}

    public void setRating(Integer rating){this.rating = rating;}
    public void setRatedId(Integer ratedId){this.ratedId = ratedId;}
    public void setRatedById(Integer ratedById){this.ratedById = ratedById;}
    public void setId(Integer id){this.id = id;}

}
