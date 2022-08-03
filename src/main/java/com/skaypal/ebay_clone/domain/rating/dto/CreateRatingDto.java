package com.skaypal.ebay_clone.domain.rating.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class CreateRatingDto {

    @Null
    Integer ratedById;

    @NotNull
    Integer ratedId;

    @Min(0)
    @Max(5)
    Integer rating;

    public CreateRatingDto(){}

    public Integer getRatedById(){return this.ratedById;}
    public Integer getRatedId(){return this.ratedId;}
    public Integer getRating(){return this.rating;}

    public void setRatedById(Integer ratedById){this.ratedById = ratedById;}
    public void setRatedId(Integer ratedId){this.ratedId = ratedId;}
    public void setRating(Integer rating){this.rating = rating;}
}
