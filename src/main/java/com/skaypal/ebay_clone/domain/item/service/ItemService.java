package com.skaypal.ebay_clone.domain.item.service;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import com.skaypal.ebay_clone.utils.Responses;
import com.skaypal.ebay_clone.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ItemService {

    ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) { this.itemRepository = itemRepository }

    public List<Item> getItems() { return itemRepository.findAll(); }

    public Item getItem(Integer id) {
        return itemRepository.findById().orElseThrow(() ->  //ADD EXCEPTION
    }

    public Item createItem(CreateItemDto createItemDto) {
        //MAYBE ADD VALIDATOR
        Item item = new Item(createItemDto);
        return itemRepository.save(item);
    }

    public Item updateItem(UpdateItemDto)
}
