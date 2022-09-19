package com.skaypal.ebay_clone.domain.item.validator;

import static com.skaypal.ebay_clone.domain.item.ItemStatusEnum.*;
import static com.skaypal.ebay_clone.domain.item.model.ItemFields.*;


import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.model.ItemFields;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.domain.item.validator.steps.*;
import com.skaypal.ebay_clone.domain.user.exceptions.UserNotFoundException;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.utils.geo.LatLongMapped;
import com.skaypal.ebay_clone.utils.validator.AlwaysValid;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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

    public boolean validateItemBidEligibility(Item item) {


        if (BOUGHT_TIMEOUT.equals(item.getStatus()) || NOT_BOUGHT.equals(item.getStatus()) || BOUGHT_BUYOUT.equals(item.getStatus()) || item.hasExpired())
            return false;

        return true;

    }

    public boolean validateItemBidEligibility(Integer id) {

        Optional<Item> optionalItem = itemRepository.findById(id);
        Item item = optionalItem.orElseThrow(()-> new ItemNotFoundException("id",id.toString() ));


        if (BOUGHT_TIMEOUT.equals(item.getStatus()) || NOT_BOUGHT.equals(item.getStatus()) || BOUGHT_BUYOUT.equals(item.getStatus()) || item.hasExpired())
            return false;

        return true;

    }

    public boolean isHigherThanMinBid(Integer itemId,Float price) {

        Optional<Item> item = itemRepository.findById(itemId);

        Item i = item.orElseThrow(() -> new ItemNotFoundException("id",itemId.toString()));

        return i.getMinBid() <= price;
    }


    public ValidationResult validateUpdateItemDto(UpdateItemDto updateItemDto) {

        ValidationStep<UpdateItemDto> steps = new ItemIsEligibleForUpdate(itemRepository);
        List<ItemFields> updatedFields = updateItemDto.getToUpdate();

        for (ItemFields field : updateItemDto.getToUpdate()) {
            switch (field) {
                case NAME:
                    break;
                case BUY_PRICE:
                    if (!updatedFields.contains(MIN_BID)) steps.linkWith(new UpdatedBuyPriceValidation(itemRepository));
                    break;
                case DESCRIPTION:
                    break;
                case MIN_BID:
                    if (!updatedFields.contains(BUY_PRICE)) steps.linkWith(new UpdatedMinBidValidation(itemRepository));
                    break;
                case END_DATE:
                    if (!updatedFields.contains(START_DATE)) steps.linkWith(new UpdatedEndDateValidation(itemRepository));
                    break;
                case LATITUDE:
                    break;
                case LONGITUDE:
                    break;
                case START_DATE:
                    if (!updatedFields.contains(END_DATE)) steps.linkWith(new UpdatedStartDateValidation(itemRepository));
                    break;
            }
        }

        return steps.validate(updateItemDto);
    }

    public boolean itemExists(int id){
        return !itemRepository.findById(id).isEmpty();
    }

}
