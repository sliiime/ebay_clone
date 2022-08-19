package com.skaypal.ebay_clone.domain.bid.repository;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BidRepository {
    public Integer getTotalBidsOfItem(Integer itemId);

    public List<Bid> getBidsOfItem(Integer itemId);

    public Bid save(Bid bid);

    public Optional<Bid> findById(Integer id);
}
