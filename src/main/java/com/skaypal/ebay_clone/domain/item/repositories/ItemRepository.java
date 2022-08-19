package com.skaypal.ebay_clone.domain.item.repositories;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;

import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    public User sellerOfItem(int itemId);
    public Item save(Item item);
    public Optional<Item> findById(Integer id);

    public List<Item> findAll();
    public void delete(Item id);

    public Integer getNumOfBids(Integer itemId);

    public Bid getHighestBid(Integer itemId);


}
