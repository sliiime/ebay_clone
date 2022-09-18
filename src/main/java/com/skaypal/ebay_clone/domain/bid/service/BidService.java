package com.skaypal.ebay_clone.domain.bid.service;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.dto.ViewBidDto;
import com.skaypal.ebay_clone.domain.bid.exceptions.BidBadRequestException;
import com.skaypal.ebay_clone.domain.bid.exceptions.BidNotFoundException;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.bid.validator.BidValidator;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.service.ItemService;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.Validation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BidService {
    BidRepository bidRepository;

    ItemService itemService;

    BidValidator bidValidator;

    @Autowired
    public BidService(BidRepository bidRepository, BidValidator bidValidator, ItemService itemService) {
        this.bidRepository = bidRepository;
        this.bidValidator = bidValidator;
        this.itemService = itemService;

    }

    public ViewBidDto getBid(Integer id) {


        Optional<Bid> bid =  bidRepository.findById(id);
        return new ViewBidDto(bid.orElseThrow(() -> new BidNotFoundException("id",id.toString())));

    }


    public ViewBidDto createBid(CreateBidDto createBidDto) {
        ValidationResult validationResult = bidValidator.validateCreateBidDto(createBidDto);
        if (!validationResult.isValid()) throw new BidBadRequestException(validationResult.getErrorMessage());
        Float buyoutPrice = itemService.getBuyoutPrice(createBidDto.getItemId());

        Float bidPrice =  buyoutPrice < createBidDto.getPrice() ?
                 buyoutPrice :
                createBidDto.getPrice();

        createBidDto.setPrice(bidPrice);

        Bid bid = new Bid(createBidDto);
        ViewBidDto view = new ViewBidDto(bidRepository.save(bid));
        itemService.newBidSubmitted(bid);
        return view;
    }

    public List<ViewBidDto> getItemBids(Integer itemId) {

        ValidationResult validationResult = bidValidator.itemExists(itemId);

        if (!validationResult.isValid()) throw new ItemNotFoundException(itemId);

        List<ViewBidDto> itemBids = bidRepository.getBidsOfItem(itemId).stream().map(ViewBidDto::new).collect(Collectors.toList());

        return itemBids;
    }
}
