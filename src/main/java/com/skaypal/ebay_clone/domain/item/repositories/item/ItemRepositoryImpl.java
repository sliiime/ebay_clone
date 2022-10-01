package com.skaypal.ebay_clone.domain.item.repositories.item;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.queries.Filter;
import com.skaypal.ebay_clone.domain.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    JPAItemRepository jpaItemRepository;
    BidRepository bidRepository;

    @Autowired
    public ItemRepositoryImpl(JPAItemRepository jpaItemREpository,
                              BidRepository bidRepository) {
        this.jpaItemRepository = jpaItemREpository;
        this.bidRepository = bidRepository;
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return jpaItemRepository.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return jpaItemRepository.findAll();
    }

    @Override
    public Item save(Item item) {
        return jpaItemRepository.save(item);
    }


    @Override
    public void delete(Item item) {
        jpaItemRepository.delete(item);
    }

    @Override
    public User sellerOfItem(int itemId) {
        return jpaItemRepository.sellerOfItem(itemId);
    }

    @Override
    public Integer getNumOfBids(Integer itemId) {
        return bidRepository.getTotalBidsOfItem(itemId);
    }

    @Override
    public Bid getHighestBid(Integer itemId) {
        List<Bid> bids = bidRepository.getBidsOfItem(itemId);
        if (bids.size() == 0) return null;
        return bids.get(0);
    }

    @Override
    public Float getBuyoutPrice(Integer itemId) {
        return jpaItemRepository.getBuyoutPrice(itemId);
    }

    @Override
    public Float getMinimumPossibleBid(int id){return jpaItemRepository.getMinBid(id);}

    @Override
    public Date getStartDate(int id){return jpaItemRepository.getStartDate(id);}

    @Override
    public Date getEndDate(int id){return jpaItemRepository.getEndDate(id);}
    @Override
    @Transactional
    public void itemBought(Integer itemId, Integer boughtBy) {
        jpaItemRepository.itemBought(itemId, new User(boughtBy));
    }

    @Override
    public int getTotalItemsCount(){
        return jpaItemRepository.countItemsById();
    }

    @Override
    public int xBoughtFromYCount(Integer receiverId, Integer senderId) {
        return jpaItemRepository.xBoughtFromYCount(receiverId, senderId);
    }

    @Override
    public Page<Item> findAll(Pageable pageable) {
        return jpaItemRepository.findAll(pageable);
    }

    @Override
    public Page<Item> findItemsOfUser(Integer userId,Pageable pageable){
        return jpaItemRepository.findItemsBySeller(new User(userId),pageable);
    }

    @Override
    public Page<Item> findAll(List<Filter> filters,Pageable pageable) {
        Specification<Item> specification = getSpecificationFromFilters(filters);
        return specification == null ? jpaItemRepository.findAll(pageable) : jpaItemRepository.findAll(specification,pageable);
    }

    private Specification<Item> createSpecification(Filter filter) {

        switch (filter.getQueryOperator()) {
            case EQUALS:
                return (root, query, criteriaBuilder) -> {
                    var field = "seller".equals(filter.getField()) ?
                            root.get(filter.getField()).get("id") :
                            root.get(filter.getField());

                    return criteriaBuilder.equal(field,
                            castToRequiredType(field.getJavaType(), filter.getValue()));
                };
            case NOT_EQUALS:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.notEqual(root.get(filter.getField()),
                                castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case GREATER_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.gt(root.get(filter.getField()),
                                (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case LESS_THAN:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.lt(root.get(filter.getField()),
                                (Number) castToRequiredType(root.get(filter.getField()).getJavaType(), filter.getValue()));
            case LIKE:
                return (root, query, criteriaBuilder) ->
                        criteriaBuilder.like(root.get(filter.getField()),
                                "%" + filter.getValue() + "%");
            case IN:
                return (root, query, criteriaBuilder) -> {
                    Join<Item,Category> itemCategory = root.join("categories");
                    var field = "category".equals(filter.getField()) ?
                            itemCategory.get("category") :
                            root.get(filter.getField());

                    query.distinct(true);


                    return  criteriaBuilder.in(field)
                            .value(castToRequiredType(field.getJavaType(), filter.getValues()));
                };
            default:
                throw new RuntimeException("Operation not supported yet");
        }
    }

    private Object castToRequiredType(Class fieldType, String value) {
        if (fieldType.isAssignableFrom(Double.class)) return Double.valueOf(value);
        else if (fieldType.isAssignableFrom(String.class)) return value;
        else if (fieldType.isAssignableFrom(Integer.class)) return Integer.valueOf(value);
        else if (fieldType.isAssignableFrom(Float.class)) return Float.valueOf(value);
        return null;
    }

    private Object castToRequiredType(Class fieldType,List<String> values){
        List<Object> list = new ArrayList<>();
        for(String value : values){
            list.add(castToRequiredType(fieldType,value));
        }
        return list;
    }

    private Specification<Item> getSpecificationFromFilters(List<Filter> filters){
        Specification<Item> specification = null;
        if (filters.size() > 0 ) {
             specification = Specification.where(createSpecification(filters.remove(0)));
            for (Filter filter : filters) specification = specification.and(createSpecification(filter));
        }

        return specification;
    }
}
