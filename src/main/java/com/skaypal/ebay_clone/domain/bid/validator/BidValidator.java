package com.skaypal.ebay_clone.domain.bid.validator;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.bid.validator.steps.SelfBidValidation;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BidValidator {

    BidRepository bidRepository;
    ItemValidator itemValidator;

    @Autowired
    public BidValidator(BidRepository bidRepository,
                        ItemValidator itemValidator){
        this.bidRepository = bidRepository;
        this.itemValidator = itemValidator;
    }


    public ValidationResult validateCreateBidDto(CreateBidDto createBidDto) {

        //Validate that the user that owns the Item doesn't submit the bid.
        //Validate that the auction that the bid refers to hasn't ended.
        //Validate that the bid has a higher value than the current highest bid.


        return new SelfBidValidation(itemValidator).validate(createBidDto);
    }
}
