package com.skaypal.ebay_clone.domain.bid.repository;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class BidRepositoryImpl implements BidRepository{

    private JPABidRepository jpaBidRepository;

    @Autowired
    public BidRepositoryImpl(JPABidRepository jpaBidRepository){
        this.jpaBidRepository = jpaBidRepository;
    }

    @Override
    public Optional<Bid> findById(Integer id) {
        return jpaBidRepository.findById(id);
    }

    @Override
    public Bid save(Bid bid) {
        return jpaBidRepository.save(bid);
    }

    @Override
    public List<Bid> getBidsOfItem(Integer itemId) {
        return jpaBidRepository.getBidsOfItem(itemId);
    }

    @Override
    public Integer getTotalBidsOfItem(Integer itemId) {
        return jpaBidRepository.getTotalBidsOfItem(itemId);
    }
}
