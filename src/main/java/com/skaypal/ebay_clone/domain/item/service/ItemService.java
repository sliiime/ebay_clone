package com.skaypal.ebay_clone.domain.item.service;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    ItemRepository itemRepository;

    private final Integer ITEM_PAGE_SIZE = 2;

    @Autowired
    public ItemService(ItemRepository itemRepository) { this.itemRepository = itemRepository; }

    public List<ViewItemDto> getItems() { return itemRepository.findAll().stream().map((i)->new ViewItemDto(i)).collect(Collectors.toList()); }

    public ViewItemDto getItem(Integer id) {

        ViewItemDto viewItemDto = new ViewItemDto(itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id",id.toString())));
        viewItemDto.setNumOfBids(itemRepository.getNumOfBids(id));

        Bid highestBid = itemRepository.getHighestBid(id);

        if (highestBid == null){
            viewItemDto.setBestBid(null);
            viewItemDto.setHighestBidderId(null);
        }else {
            viewItemDto.setBestBid(highestBid.getPrice());
            viewItemDto.setHighestBidderId(highestBid.getBidder().getId());
        }

        return viewItemDto;
    }

    public ViewItemDto createItem(CreateItemDto createItemDto) {
        Item item = new Item(createItemDto);
        return new ViewItemDto(itemRepository.save(item));
    }

    public void updateItem(UpdateItemDto updateItemDto) {
        Item item = itemRepository.findById(updateItemDto.getId()).orElseThrow(() -> new ItemNotFoundException("id",updateItemDto.getId().toString()));
        item.updateItemWithDto(updateItemDto);

        itemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id", id.toString()));
        itemRepository.delete(item);
    }

    public Page<ViewItemDto> getPage(Integer p) {
        Page<Item> itemPage = itemRepository.findAll(PageRequest.of(p,ITEM_PAGE_SIZE));
        Page<ViewItemDto> viewItemDtoPage = itemPage.map(item -> new ViewItemDto(item));

        return viewItemDtoPage;
    }
}