package com.skaypal.ebay_clone.domain.interaction.model;

import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;

import javax.persistence.*;


@Table(name = "user_item_interaction")
@Entity
public class Interaction {

    public Interaction(User user, Item item, InteractionStatus interactionStatus){
        this.user = user;
        this.item = item;
        this.interactionStatus = interactionStatus;
    }
    protected Interaction(){}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Item item;

    @Column(name = "interaction")
    @Enumerated(EnumType.STRING)
    private InteractionStatus interactionStatus;


    public Integer getId(){return this.id;}
    public User getUser(){return this.user;}
    public Item getItem(){return this.item;}
    public InteractionStatus getInteractionStatus(){return this.interactionStatus;}

    public void setId(Integer id){this.id = id;}
    public void setUser(User user){this.user = user; }
    public void setItem(Item item){this.item = item;}
    public void setInteractionStatus(InteractionStatus interactionStatus){this.interactionStatus = interactionStatus;}


}
