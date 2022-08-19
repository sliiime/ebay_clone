package com.skaypal.ebay_clone.domain.item.validator;

import com.skaypal.ebay_clone.domain.item.repositories.JPAItemRepository;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemValidator {

    public JPAItemRepository JPAItemRepository;

    @Autowired
    public ItemValidator(JPAItemRepository JPAItemRepository){
           this.JPAItemRepository = JPAItemRepository;
    }


    public boolean usersHaveMadeTransaction(Integer receiverId, Integer senderId) {
        return true;
    }

    public boolean isSellerOfItem(Integer itemId, Integer sellerId){
        User seller = JPAItemRepository.sellerOfItem(itemId);

        if (seller == null) throw new UserNotFoundException("A seller with this id does not exist");

        return seller.getId().equals(sellerId);
    }
}
