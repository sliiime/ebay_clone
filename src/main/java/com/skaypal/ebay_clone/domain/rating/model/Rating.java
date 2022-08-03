package com.skaypal.ebay_clone.domain.rating.model;


import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.user.model.User;

import javax.persistence.*;

@Entity
@Table
public class Rating {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;

    private Integer rating;

    @ManyToOne
    private User rated;

    @ManyToOne
    private User ratedBy;

    public Rating(CreateRatingDto createRatingDto){
        this.rated = new User(createRatingDto.getRatedId());
        this.ratedBy = new User(createRatingDto.getRatedById());
        this.rating = createRatingDto.getRating();
    }

    public Integer getId() { return id;}

    public Integer getRating() { return rating;}

    public User getRated() { return rated;}

    public User getRatedBy() { return ratedBy;}

    public void setId(Integer id) { this.id = id;}
    public void setRating(Integer rating) { this. rating = rating;}
    public void setRated(User rated){this.rated = rated;}
    public void setRatedBy(User ratedBy){this.ratedBy = rated;}






}
