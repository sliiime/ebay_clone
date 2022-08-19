package com.skaypal.ebay_clone.domain.bid.controller;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.dto.ViewBidDto;
import com.skaypal.ebay_clone.domain.bid.service.BidService;
import com.skaypal.ebay_clone.utils.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping(path = "ebay_clone/api/bid")
public class BidController {

    BidService bidService;

    private final String location = "ebay_clone/api/bid";

    @Autowired
    public BidController(BidService bidService){
        this.bidService = bidService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewBidDto> getBid(@PathVariable Integer id){
        return ResponseEntity.ok(bidService.getBid(id));
    }

    @PostMapping
    public ResponseEntity<?> createBid(@RequestBody CreateBidDto createBidDto){
        createBidDto.setBidderId(2);
        createBidDto.setSubmissionDate(new Date());
        ViewBidDto viewBidDto = bidService.createBid(createBidDto);

        return Responses.created(String.format("%s/%s", location, viewBidDto.getId().toString()));

    }
}
