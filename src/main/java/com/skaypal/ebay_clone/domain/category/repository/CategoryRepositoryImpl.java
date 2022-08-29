package com.skaypal.ebay_clone.domain.category.repository;

import com.skaypal.ebay_clone.domain.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class CategoryRepositoryImpl implements  CategoryRepository{

    JPACategoryRepository jpaCategoryRepository;

    @Autowired
    public CategoryRepositoryImpl(JPACategoryRepository jpaCategoryRepository){
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    public Optional<Category> findByCategory(String category){
        return jpaCategoryRepository.findCategoryByCategory(category);
    }

    public List<Category> findAll(){
        return jpaCategoryRepository.findAll();
    }
}
