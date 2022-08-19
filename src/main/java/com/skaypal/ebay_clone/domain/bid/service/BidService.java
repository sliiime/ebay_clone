package com.skaypal.ebay_clone.domain.bid.service;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.dto.ViewBidDto;
import com.skaypal.ebay_clone.domain.bid.exceptions.BidBadRequestException;
import com.skaypal.ebay_clone.domain.bid.exceptions.BidNotFoundException;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.bid.validator.BidValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BidService {
    BidRepository bidRepository;

    BidValidator bidValidator;

    @Autowired
    public BidService(BidRepository bidRepository, BidValidator bidValidator) {
        this.bidRepository = bidRepository;
        this.bidValidator = bidValidator;

    }

    public ViewBidDto getBid(Integer id) {

        Optional<Bid> bid =  bidRepository.findById(id);
        return new ViewBidDto(bid.orElseThrow(() -> new BidNotFoundException("id",id.toString())));

    }

    public ViewBidDto createBid(CreateBidDto createBidDto) {
        ValidationResult validationResult = bidValidator.validateCreateBidDto(createBidDto);
        if (!validationResult.isValid()) throw new BidBadRequestException(validationResult.getErrorMessage());
        Bid bid = new Bid(createBidDto);
        return new ViewBidDto(bidRepository.save(bid));
    }
}
