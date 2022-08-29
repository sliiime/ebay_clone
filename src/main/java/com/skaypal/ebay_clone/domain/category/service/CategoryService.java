package com.skaypal.ebay_clone.domain.category.service;

import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CategoryService {

    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
            this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategory(String category) {

        return categoryRepository.findByCategory(category);
    }
}
