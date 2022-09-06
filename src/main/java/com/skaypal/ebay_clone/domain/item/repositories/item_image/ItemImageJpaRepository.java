package com.skaypal.ebay_clone.domain.item.repositories.item_image;

import com.skaypal.ebay_clone.domain.item.model.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemImageJpaRepository extends JpaRepository<ItemImage,Integer> {

    public Optional<ItemImage> findByRelativePath(String relativePath);

}
