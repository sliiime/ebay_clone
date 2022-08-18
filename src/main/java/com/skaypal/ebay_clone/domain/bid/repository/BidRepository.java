package com.skaypal.ebay_clone.domain.bid.repository;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid,Integer> {

}
