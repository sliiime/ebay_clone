package com.skaypal.ebay_clone.domain.item.repositories.item;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.queries.Filter;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;
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

    public Page<Item> findAll(Pageable pageable);

    public Page<Item> findAll(List<Filter> filters, Pageable pageable);

    public Page<Item> findItemsOfUser(Integer userId,Pageable pageable);

    public Float getBuyoutPrice(Integer id);

    public Date getStartDate(int id);

    public Date getEndDate(int id);
    public Float getMinimumPossibleBid(int id);
    public void itemBought(Integer itemId,Integer boughtById);

    int xBoughtFromYCount(Integer receiverId, Integer senderId);

    int getTotalItemsCount();


    Page<Item> getRecommendations(List<Integer> itemList, User user,Pageable pageable);
}
