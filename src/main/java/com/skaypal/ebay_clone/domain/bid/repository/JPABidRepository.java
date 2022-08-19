package com.skaypal.ebay_clone.domain.bid.repository;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JPABidRepository extends JpaRepository<Bid,Integer> {
    @Query("SELECT COUNT(b) FROM Bid b WHERE b.item.id  = ?1")
    public Integer getTotalBidsOfItem(Integer itemId);

    @Query("SELECT b FROM Bid b ORDER BY b.price DESC ")
    public List<Bid> getBidsOfItem(Integer itemId);





}
