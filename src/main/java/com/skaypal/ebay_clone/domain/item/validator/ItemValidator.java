package com.skaypal.ebay_clone.domain.item.validator;

import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemValidator {

    public ItemRepository itemRepository;

    @Autowired
    public ItemValidator(ItemRepository itemRepository){
           this.itemRepository = itemRepository;
    }

    public boolean usersHaveMadeTransaction(Integer receiverId, Integer senderId) {
        return true;
    }

    public boolean isSellerOfItem(Integer itemId, Integer sellerId){
        User seller = itemRepository.sellerOfItem(itemId);

        if (seller == null) throw new UserNotFoundException("A seller with this id does not exist");

        return seller.getId().equals(sellerId);
    }
}
