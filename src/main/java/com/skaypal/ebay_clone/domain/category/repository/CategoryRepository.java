package com.skaypal.ebay_clone.domain.category.repository;

import com.skaypal.ebay_clone.domain.category.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();
    Optional<Category> findByCategory(String category);

}
