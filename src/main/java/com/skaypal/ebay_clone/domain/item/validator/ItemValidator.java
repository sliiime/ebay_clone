package com.skaypal.ebay_clone.domain.item.validator;

import static com.skaypal.ebay_clone.domain.item.ItemStatusEnum.*;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import com.skaypal.ebay_clone.domain.item.repositories.JPAItemRepository;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ItemValidator {

    public ItemRepository itemRepository;

    @Autowired
    public ItemValidator(ItemRepository itemRepository){
           this.itemRepository = itemRepository;
    }


    public boolean usersHaveMadeTransaction(Integer receiverId, Integer senderId) {
        return itemRepository.xBoughtFromYCount(receiverId,senderId) > 0 || itemRepository.xBoughtFromYCount(senderId,receiverId) > 0 ;
    }

    public boolean isSellerOfItem(Integer itemId, Integer sellerId){
        User seller = itemRepository.sellerOfItem(itemId);

        if (seller == null) throw new UserNotFoundException("A seller with this id does not exist");

        return seller.getId().equals(sellerId);
    }

    public boolean auctionIsEligibleForBids(Integer itemId) {
        Optional<Item> item = itemRepository.findById(itemId);

        Item i = item.orElseThrow(() -> new ItemNotFoundException("id",itemId.toString()));

        if (BOUGHT_TIMEOUT.equals(i.getStatus()) || NOT_BOUGHT.equals(i.getStatus()) || BOUGHT_BUYOUT.equals(i.getStatus()))
            return false;
        else if (i.hasExpired()){
            if (itemRepository.getNumOfBids(itemId) > 0) i.setStatus(BOUGHT_TIMEOUT);
            else i.setStatus(NOT_BOUGHT);
            return false;
        }

        return true;

    }

    public boolean isHigherThanMinBid(Integer itemId,Float price) {

        Optional<Item> item = itemRepository.findById(itemId);

        Item i = item.orElseThrow(() -> new ItemNotFoundException("id",itemId.toString()));

        return i.getMinBid() <= price;
    }
}
