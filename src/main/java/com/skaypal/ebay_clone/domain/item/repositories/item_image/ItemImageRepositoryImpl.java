package com.skaypal.ebay_clone.domain.item.repositories.item_image;

import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.model.ItemImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ItemImageRepositoryImpl implements ItemImageRepository {


    ItemImageJpaRepository imageJpaRepository;

    @Autowired
    public ItemImageRepositoryImpl(ItemImageJpaRepository itemImageJpaRepository){
            this.imageJpaRepository = itemImageJpaRepository;
    }


    @Override
    public ItemImage save(ItemImage itemImage){
        return imageJpaRepository.save(itemImage);
    }

    @Override
    public List<ItemImage> findByItem(int id){
        return imageJpaRepository.findByItem(new Item(id));
    }
}
