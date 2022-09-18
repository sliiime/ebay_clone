package com.skaypal.ebay_clone.domain.bid.controller;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.dto.ViewBidDto;
import com.skaypal.ebay_clone.domain.bid.service.BidService;
import com.skaypal.ebay_clone.utils.Responses;
import com.skaypal.ebay_clone.utils.jwt.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.xml.bind.JAXBContext;
import java.util.Date;

@RestController
@RequestMapping(path = "ebay_clone/api/bid")
public class BidController {

    BidService bidService;
    JWTUtil jwtUtil;

    private final String location = "ebay_clone/api/bid";

    @Autowired
    public BidController(BidService bidService,
                         JWTUtil jwtUtil){

        this.bidService = bidService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ViewBidDto> getBid(@PathVariable Integer id){
        return ResponseEntity.ok(bidService.getBid(id));
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<?> createBid(@Valid @ModelAttribute CreateBidDto createBidDto, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        
        createBidDto.setBidderId(jwtUtil.retrieveUserId(token));
        createBidDto.setSubmissionDate(new Date());
        ViewBidDto viewBidDto = bidService.createBid(createBidDto);

        return Responses.created(String.format("%s/%s", location, viewBidDto.getId().toString()));

    }
}
