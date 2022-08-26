package com.skaypal.ebay_clone.domain.item.repositories;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    JPAItemRepository jpaItemRepository;
    BidRepository bidRepository;

    @Autowired
    public ItemRepositoryImpl(JPAItemRepository jpaItemREpository,
                              BidRepository bidRepository){
        this.jpaItemRepository = jpaItemREpository;
        this.bidRepository = bidRepository;
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return jpaItemRepository.findById(id);
    }

    @Override
    public List<Item> findAll(){return jpaItemRepository.findAll();}

    @Override
    public Item save(Item item) {
       return jpaItemRepository.save(item);
    }

    @Override
    public void delete(Item item) {
         jpaItemRepository.delete(item);
    }

    @Override
    public User sellerOfItem(int itemId){
        return jpaItemRepository.sellerOfItem(itemId);
    }

    @Override
    public Integer getNumOfBids(Integer itemId) {
        return bidRepository.getTotalBidsOfItem(itemId);
    }

    public Bid getHighestBid(Integer itemId){
        List<Bid> bids = bidRepository.getBidsOfItem(itemId);
        if (bids.size() == 0) return null;
        return bids.get(0);
    }

    public Float getBuyoutPrice(Integer itemId){
        return jpaItemRepository.getBuyoutPrice(itemId);
    }

    @Override
    @Transactional
    public void itemBought(Integer itemId,Integer boughtBy){
        jpaItemRepository.itemBought(itemId,new User(boughtBy));
    }

    @Override
    public int xBoughtFromYCount(Integer receiverId, Integer senderId) {
        return jpaItemRepository.xBoughtFromYCount(receiverId,senderId);
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return jpaItemRepository.findAll(pageable);
    }
}
