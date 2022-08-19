package com.skaypal.ebay_clone.domain.rating.repositories;

import com.skaypal.ebay_clone.domain.rating.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARatingRepository extends JpaRepository<Rating,Integer>{ }
