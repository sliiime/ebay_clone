package com.skaypal.ebay_clone.domain.item.repositories.item_image;

import com.skaypal.ebay_clone.domain.item.model.ItemImage;

import java.util.Optional;

public interface ItemImageRepository {

    public ItemImage save(ItemImage itemImage);
    public Optional<ItemImage> findByRelativePath(String relativePath);
}
