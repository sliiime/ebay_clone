package com.skaypal.ebay_clone.domain.item.repositories;

import com.skaypal.ebay_clone.domain.item.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item,Integer> {
}
