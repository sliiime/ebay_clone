package com.skaypal.ebay_clone.domain.item.repositories.item_image;

import com.skaypal.ebay_clone.domain.item.model.ItemImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class ItemImageRepositoryImpl implements ItemImageRepository {


    ItemImageJpaRepository imageJpaRepository;

    @Autowired
    public ItemImageRepositoryImpl(ItemImageJpaRepository itemImageJpaRepository){
            this.imageJpaRepository = itemImageJpaRepository;
    }

    @Override
    public Optional<ItemImage> findByRelativePath(String relativePath) {
        return imageJpaRepository.findByRelativePath(relativePath);
    }

    @Override
    public ItemImage save(ItemImage itemImage){
        return imageJpaRepository.save(itemImage);
    }
}
