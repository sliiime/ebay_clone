package com.skaypal.ebay_clone.domain.item.controller;

import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.service.ItemService;
import com.skaypal.ebay_clone.utils.Responses;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/item")
@Validated
public class ItemController {

    public static final String location = "ebay_clone/api/item";

    private final ItemService itemService;

    private final JWTUtil jwtUtil;

    @Autowired
    public ItemController(ItemService itemService,
                          JWTUtil jwtUtil) {

        this.itemService = itemService;
        this.jwtUtil = jwtUtil;
    }

    /*@GetMapping
    public ResponseEntity<List<ViewItemDto>> getItems() {
        List<ViewItemDto> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }*/

    @GetMapping
    public ResponseEntity<Page<ViewItemDto>> getItemsPage(@RequestParam Integer p) {
        return ResponseEntity.ok(itemService.getPage(p));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewItemDto> getItem(@PathVariable Integer id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @PostMapping
    public ResponseEntity<?> createItem(@Valid @RequestBody CreateItemDto createItemDto, HttpServletRequest request) {

        String token = request.getHeader("Authorization"); //Check whether this header exists;
        Integer userId = jwtUtil.retrieveUserId(token);       //Throws invalid token exception
        createItemDto.setOwnerId(userId);
        createItemDto.setStartDate(new Date());
        ViewItemDto item = itemService.createItem(createItemDto);
        return Responses.created(location + "/" + item.getId());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Integer id, @Valid @RequestBody UpdateItemDto updateItemDto) {
        updateItemDto.setId(id);
        itemService.updateItem(updateItemDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();

    }
}
