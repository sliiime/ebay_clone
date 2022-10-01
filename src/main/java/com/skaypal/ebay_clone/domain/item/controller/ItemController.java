package com.skaypal.ebay_clone.domain.item.controller;

import com.skaypal.ebay_clone.domain.item.dto.*;
import com.skaypal.ebay_clone.domain.item.repositories.queries.Filter;
import com.skaypal.ebay_clone.domain.item.repositories.queries.QueryOperator;
import com.skaypal.ebay_clone.domain.item.service.ItemService;
import com.skaypal.ebay_clone.domain.interaction.service.InteractionService;
import com.skaypal.ebay_clone.domain.recommendation.service.RecommendationService;
import com.skaypal.ebay_clone.utils.Responses;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.skaypal.ebay_clone.domain.interaction.model.InteractionStatus.VIEWED;

@RestController
@RequestMapping(path = "ebay_clone/api/item")
@Validated
public class ItemController {

    public static final String location = "ebay_clone/api/item";

    private final ItemService itemService;

    private final JWTUtil jwtUtil;

    private final InteractionService interactionService;

    private final RecommendationService recommendationService;

    @Autowired
    public ItemController(ItemService itemService,
                          InteractionService interactionService,
                          RecommendationService recommendationService,
                          JWTUtil jwtUtil) {

        this.itemService = itemService;
        this.jwtUtil = jwtUtil;
        this.interactionService = interactionService;
        this.recommendationService = recommendationService;

    }

    /*@GetMapping
    public ResponseEntity<List<ViewItemDto>> getItems() {
        List<ViewItemDto> items = itemService.getItems();
        return ResponseEntity.ok(items);
    }*/


    @PostMapping(path = "/search/")
    public ResponseEntity<Page<ViewItemDto>> getItemsPage(@RequestParam Integer p, @RequestBody Optional<FiltersDto> filters, HttpServletRequest request) {


        FiltersDto filtersDto = filters.isPresent() ? filters.get() : null;

        Page<ViewItemDto> viewItemDtoPage = itemService.getPage(filtersDto, p);


        String token = request.getHeader("Authorization");

        if (token != null) {

            Integer userId = jwtUtil.retrieveUserId(token);

            interactionService.initializeInteractions(viewItemDtoPage, userId);

        }


        return ResponseEntity.ok(viewItemDtoPage);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewItemDto> getItem(@PathVariable("id") Integer itemId, HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token != null) {
            Integer userId = jwtUtil.retrieveUserId(token);

            interactionService.itemViewed(userId, itemId);
        }


        return ResponseEntity.ok(itemService.getItem(itemId));

    }

    @GetMapping(path = "/user/")
    public ResponseEntity<Page<ViewItemDto>> getUserItems(@RequestParam Integer p, HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        Integer userId = jwtUtil.retrieveUserId(token);

        FiltersDto filtersDto = new FiltersDto();
        List<Filter> filters = new ArrayList<>();
        filters.add(new Filter("seller", QueryOperator.EQUALS, userId.toString()));

        filtersDto.setFilters(filters);

        return ResponseEntity.ok(itemService.getPage(filtersDto, p));

    }

    /*@GetMapping(path ="/images/{id}")
    public ResponseEntity<List<ItemImageDto>> getItemImages(@PathVariable Integer id){

            return ResponseEntity.ok(itemService.getItemImages(id)) ;


    }*/

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createItem(@Valid @ModelAttribute CreateItemDto createItemDto, HttpServletRequest request) throws IOException {

        String token = request.getHeader("Authorization"); //Check whether this header exists;
        Integer userId = jwtUtil.retrieveUserId(token);       //Throws invalid token exception
        createItemDto.setOwnerId(userId);

        Date startDate = createItemDto.getStartDate() == null ? new Date() : createItemDto.getStartDate();
        createItemDto.setStartDate(startDate);

        ViewItemDto item = itemService.createItem(createItemDto);

        return Responses.created(location + "/" + item.getId());
    }

    @PutMapping(path = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateItem(@PathVariable Integer id, @Valid @ModelAttribute UpdateItemDto updateItemDto) {
        updateItemDto.setId(id);
        itemService.updateItem(updateItemDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Integer id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();

    }

    @GetMapping(path = "/recommendations")
    public ResponseEntity<?> getRecommendations(HttpServletRequest request) {

        String token = request.getHeader("Authorization");


        Integer userId = jwtUtil.retrieveUserId(token);

        return ResponseEntity.ok(recommendationService.getRecommendations(userId));


    }
}
