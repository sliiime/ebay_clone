package com.skaypal.ebay_clone.domain.item.controller;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.service.ItemService;
import com.skaypal.ebay_clone.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/item")
@Validated
public class ItemController {

    public static final String location = "ebay_clone/api/item";

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) { this.itemService = itemService; }

    @GetMapping
    public ResponseEntity<List<Item>> getItems() {
        List<Item> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Integer id) { return ResponseEntity.ok(itemService.getItem(id)); }

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody CreateItemDto createItemDto){
        Item item = new Item(createItemDto);
        itemService.createItem(createItemDto);
        return Responses.created(location + "/" + item.getId());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@Valid @RequestBody UpdateItemDto updateItemDto){
        updateItemDto.setId(id);
        itemService.updateItem(updateItemDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id){
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();

    }
}
