package com.skaypal.ebay_clone.domain.bid.validator.steps;

import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.utils.validator.ValidationResult;
import com.skaypal.ebay_clone.utils.validator.ValidationStep;

import java.util.List;

public class BidIsTheHighestValidation extends ValidationStep<CreateBidDto> {

    BidRepository bidRepository;

    public BidIsTheHighestValidation(BidRepository bidRepository){
        this.bidRepository = bidRepository;
    }

    @Override
    public ValidationResult validate(CreateBidDto toValidate) {
        List<Bid> bids =  bidRepository.getBidsOfItem(toValidate.getItemId());

        return bids.size() == 0 || bids.get(0).getPrice() < toValidate.getPrice()
                ? checkNext(toValidate)
                : ValidationResult.invalid("Current bid is lower than the current best bid");
    }
}
