package com.skaypal.ebay_clone.domain.rating.controller;

import com.skaypal.ebay_clone.domain.rating.dto.CreateRatingDto;
import com.skaypal.ebay_clone.domain.rating.dto.ViewRatingDto;
import com.skaypal.ebay_clone.domain.rating.service.RatingService;
import com.skaypal.ebay_clone.utils.Responses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "ebay_clone/api/rating")
public class RatingController {

    RatingService ratingService;

    public static final String location = "ebay_clone/api/rating";

    public RatingController(RatingService ratingService){
        this.ratingService = ratingService;
    }
    @GetMapping
    public ResponseEntity<List<ViewRatingDto>> getRatings() {
        return ResponseEntity.ok(ratingService.getRatings());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewRatingDto> getRating(@PathVariable Integer id) {
        return ResponseEntity.ok(ratingService.getRating(id));
    }

    @PostMapping
    public ResponseEntity<?> createRating(@Valid @RequestBody CreateRatingDto createRatingDto) {

        createRatingDto.setRatedById(1);

        ViewRatingDto rating = ratingService.createRating(createRatingDto);


        return Responses.created(String.format("%s/%s", location, rating.getId().toString()));
    }



}
