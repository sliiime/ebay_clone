package com.skaypal.ebay_clone.domain.item.service;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.category.exceptions.CategoryNotFoundException;
import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.category.service.CategoryService;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.FiltersDto;
import com.skaypal.ebay_clone.domain.item.dto.UpdateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
import com.skaypal.ebay_clone.domain.item.repositories.queries.Filter;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.property.ImageStorageProperty;
import com.sun.xml.txw2.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.View;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    private final ItemValidator itemValidator;

    private final CategoryService categoryService;

    private final Path imageStoragePath;
    private final Integer ITEM_PAGE_SIZE = 4;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                       ItemValidator itemValidator,
                       CategoryService categoryService,
                       ImageStorageProperty imageStorageProperty) throws IOException {
        this.itemRepository = itemRepository;
        this.itemValidator = itemValidator;
        this.categoryService = categoryService;
        this.imageStoragePath = Paths.get(imageStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();

        Files.createDirectories(imageStoragePath);
    }

    public List<ViewItemDto> getItems() {
        return itemRepository.findAll().stream().map((i) -> new ViewItemDto(i)).collect(Collectors.toList());
    }

    public ViewItemDto getItem(Integer id) {

        ViewItemDto viewItemDto = new ViewItemDto(itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id", id.toString())));

        setBidData(viewItemDto);

        return viewItemDto;
    }

    private void setItemCategories(Item item,List<String> categoryNames) {

        for (String str : categoryNames){
            Optional<Category> category = categoryService.getCategory(str);
            if (category.isEmpty()) throw new CategoryNotFoundException(str);
            item.addCategory(category.get());
        }

    }

    private void saveItemImages(Path directoryPath,List<MultipartFile> images) throws IOException {
        for (MultipartFile image : images) saveItemImage(directoryPath,image);
    }

    private void saveItemImage(Path directoryPath,MultipartFile image) throws IOException {
                Files.copy(image.getInputStream(),directoryPath);
    }

    private Item saveItem(CreateItemDto createItemDto){
        Item item = new Item(createItemDto);

        List<String> categoryNames = createItemDto.getCategories();

        setItemCategories(item,categoryNames);

        itemRepository.save(item);

        return item;

    }

    public ViewItemDto createItem(CreateItemDto createItemDto) throws IOException {

        Item item = saveItem(createItemDto);

        List<MultipartFile> images = createItemDto.getImages();

        String directoryPathStr = String.format("item_%s",item.getId().toString());

        Path directoryPath = imageStoragePath.resolve(directoryPathStr);

        saveItemImages(directoryPath,images);

        return new ViewItemDto(item);
    }


    public void updateItem(UpdateItemDto updateItemDto) {
        Item item = itemRepository.findById(updateItemDto.getId()).orElseThrow(() -> new ItemNotFoundException("id", updateItemDto.getId().toString()));
        item.updateItemWithDto(updateItemDto);

        itemRepository.save(item);
    }

    public void deleteItem(Integer id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id", id.toString()));
        itemRepository.delete(item);
    }

    public Page<ViewItemDto> getPage(FiltersDto filtersDto, Integer p) {
        Page<Item> itemPage;
        if (filtersDto == null) itemPage = itemRepository.findAll(PageRequest.of(p, ITEM_PAGE_SIZE));
        else{
            List<Filter> filters = filtersDto.getFilters().stream().collect(Collectors.toCollection(ArrayList::new));
            itemPage = itemRepository.findAll(filters, PageRequest.of(p, ITEM_PAGE_SIZE));
        }
        itemPage.forEach((i) -> itemValidator.auctionIsEligibleForBids(i.getId()));
        Page<ViewItemDto> viewItemDtoPage = itemPage.map(item -> new ViewItemDto(item));
        viewItemDtoPage.forEach(i -> setBidData(i));
        return viewItemDtoPage;
    }

    /*public Page<ViewItemDto> getUserItems(Integer userId, Integer p) {
        Page<Item> itemPage = itemRepository.findItemsOfUser(userId,PageRequest.of(p,ITEM_PAGE_SIZE));
        itemPage.forEach((i) ->{
            if(i.hasExpired()){
                i.setStatus(ItemStatusEnum.BOUGHT_TIMEOUT);
                itemRepository.save(i);
            }
        });
        Page<ViewItemDto> viewItemDtoPage = itemPage.map(item -> new ViewItemDto(item));
        viewItemDtoPage.forEach(i ->setBidData(i));
        return viewItemDtoPage;
    }*/

    public boolean newBidSubmitted(Bid bid) {
        Float buyoutPrice = itemRepository.getBuyoutPrice(bid.getItem().getId());

        if (bid.getPrice() >= buyoutPrice) itemRepository.itemBought(bid.getItem().getId(), bid.getBidder().getId());

        return bid.getPrice() >= buyoutPrice;


    }

    private void setBidData(ViewItemDto viewItemDto) {

        viewItemDto.setNumOfBids(itemRepository.getNumOfBids(viewItemDto.getId()));
        Bid highestBid = itemRepository.getHighestBid(viewItemDto.getId());

        if (highestBid == null) {
            viewItemDto.setBestBid(null);
            viewItemDto.setHighestBidderId(null);
        } else {
            viewItemDto.setBestBid(highestBid.getPrice());
            viewItemDto.setHighestBidderId(highestBid.getBidder().getId());
        }

    }


    public Float getBuyoutPrice(Integer itemId) {
        return itemRepository.getBuyoutPrice(itemId);
    }
}
