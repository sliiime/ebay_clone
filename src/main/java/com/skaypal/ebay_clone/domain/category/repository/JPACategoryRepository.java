package com.skaypal.ebay_clone.domain.category.repository;

import com.skaypal.ebay_clone.domain.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPACategoryRepository extends JpaRepository<Category,Integer> {
    Optional<Category> findCategoryByCategory(String category);
}
