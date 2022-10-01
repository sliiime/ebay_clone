package com.skaypal.ebay_clone.domain.recommendation.model;

import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;

import javax.persistence.*;

@Entity
@Table(name = "user_item_recommendation")
public class Recommendation {

    public Recommendation(User user,Item item,RecommendationStatus recommendationStatus){
        this.user = user;
        this.item = item;
        this.recommendationStatus = recommendationStatus;
    }

    protected Recommendation(){}


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    @Column(name = "status")
    private RecommendationStatus recommendationStatus;

    public Integer getId(){return this.id;}
    public User getUser(){return this.user;}
    public Item getItem(){return this.item;}
    public RecommendationStatus getRecommendationStatus(){return this.recommendationStatus;}

    public void setId(Integer id){this.id = id;}
    public void setUser(User user){this.user = user;}
    public void setItem(Item item){this.item = item;}
    public void setRecommendationStatus(RecommendationStatus recommendationStatus){this.recommendationStatus = recommendationStatus;}


    public void update(Recommendation recommendation) {
        this.recommendationStatus = recommendation.recommendationStatus;
    }
}
