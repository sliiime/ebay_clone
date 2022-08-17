package com.skaypal.ebay_clone.domain.item.validator;

import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemValidator {

    public ItemRepository itemRepository;

    @Autowired
    public ItemValidator(ItemRepository itemRepository){
           this.itemRepository = itemRepository;
    }

}
