package com.skaypal.ebay_clone.domain.item.validator;

import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.JPAItemRepository;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ItemValidator {

    public JPAItemRepository jpaItemRepository;

    @Autowired
    public ItemValidator(JPAItemRepository JPAItemRepository){
           this.jpaItemRepository = JPAItemRepository;
    }


    public boolean usersHaveMadeTransaction(Integer receiverId, Integer senderId) {
        return true;
    }

    public boolean isSellerOfItem(Integer itemId, Integer sellerId){
        User seller = jpaItemRepository.sellerOfItem(itemId);

        if (seller == null) throw new UserNotFoundException("A seller with this id does not exist");

        return seller.getId().equals(sellerId);
    }

    public boolean auctionIsEligibleForBids(Integer itemId) {
        Optional<Item> item = jpaItemRepository.findById(itemId);

        Item i = item.orElseThrow(() -> new ItemNotFoundException("id",itemId.toString()));

        return i.getStatus() == ItemStatusEnum.ONGOING;

    }
}
