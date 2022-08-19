package com.skaypal.ebay_clone.domain.item.repositories;

import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface JPAItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i.seller from Item i where i.id = ?1")
    public User sellerOfItem(int itemId);

    public Page<Item> findAll(Pageable pageable);

    @Query("SELECT i.buyPrice FROM Item i WHERE i.id = ?1")
    public Float getBuyoutPrice(Integer itemId);
    @Modifying
    @Query("UPDATE Item SET status = com.skaypal.ebay_clone.domain.item.ItemStatusEnum.BOUGHT_BUYOUT WHERE id = ?1")
    public void itemBought(Integer itemId);



}
