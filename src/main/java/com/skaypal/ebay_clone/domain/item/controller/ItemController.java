package com.skaypal.ebay_clone.domain.item.controller;

import com.skaypal.ebay_clone.domain.item.service.ItemService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "ebay_clone/api/item")
@Validated
public class ItemController {

    public static final String location = "ebay_clone/api/user";

    private final ItemService itemService;
}
