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
    @JoinColumn(name ="ratedby")
    private User ratedBy;

    @ManyToOne
    @JoinColumn(name="rated")
    private User rated;

    public Rating(CreateRatingDto createRatingDto){
        this.rated = new User(createRatingDto.getRatedId());
        this.ratedBy = new User(createRatingDto.getRatedById());
        this.rating = createRatingDto.getRating();
    }

    public Rating(User ratedBy,User rated,Integer rating){
        this.ratedBy = ratedBy;
        this.rated = rated;
        this.rating = rating;
    }

    protected Rating(){}

    public Integer getId() { return id;}

    public Integer getRating() { return rating;}

    public User getRated() { return rated;}

    public User getRatedBy() { return ratedBy;}

    public void setId(Integer id) { this.id = id;}
    public void setRating(Integer rating) { this.rating = rating;}
    public void setRated(User rated){this.rated = rated;}
    public void setRatedBy(User ratedBy){this.ratedBy = ratedBy;}






}
