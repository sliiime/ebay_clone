package com.skaypal.ebay_clone.domain.item.repositories.item;

import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface JPAItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i.seller from Item i where i.id = ?1")
    public User sellerOfItem(int itemId);

    public Page<Item> findAll(Pageable pageable);
    public Page<Item> findAll(Specification<Item> specification,Pageable pageable);

    public Page<Item> findItemsBySeller(User seller,Pageable pageable);

    public Integer countItemsById();

    @Query("SELECT i.buyPrice FROM Item i WHERE i.id = ?1")
    public Float getBuyoutPrice(Integer itemId);

    @Query("SELECT i.minBid FROM Item i WHERE i.id = ?1")
    public Float getMinBid(Integer id);

    @Query("SELECT i.startDate FROM Item i WHERE i.id =?1")
    public Date getStartDate(Integer id);

    @Query("SELECT i.endDate FROM Item i WHERE i.id =?1")
    public Date getEndDate(Integer id);

    @Modifying
    @Query("UPDATE Item SET status = com.skaypal.ebay_clone.domain.item.ItemStatusEnum.BOUGHT_BUYOUT, boughtBy = ?2 WHERE id = ?1")
    public void itemBought(Integer itemId,User user);

    //public Page<Item> findItemsByBoughtBy(User user);

    @Query("SELECT count(i) FROM Item i WHERE i.boughtBy.id = ?1 AND i.seller.id = ?2")
    public int xBoughtFromYCount(Integer x,Integer y);



}
