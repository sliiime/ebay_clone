package com.skaypal.ebay_clone.domain.item.service;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) { this.itemRepository = itemRepository; }

    public List<Item> getItems() { return itemRepository.findAll(); }

    public Item getItem(Integer id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id",id.toString()));
    }

    public Item createItem(CreateItemDto createItemDto) {
        //MAYBE ADD VALIDATOR
        Item item = new Item(createItemDto);
        return itemRepository.save(item);
    }

    public void updateItem(UpdateItemDto updateItemDto) {
        //PROLLY ADD DTO VALIDATOR
        Item item = itemRepository.findById(updateItemDto.getId()).orElseThrow(() -> new ItemNotFoundException("id",updateItemDto.getId().toString()));
        item.updateItemWithDto(updateItemDto);

        itemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id", id.toString()));
        itemRepository.delete(item);
    }
}
