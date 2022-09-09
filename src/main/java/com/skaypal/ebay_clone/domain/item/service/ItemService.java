package com.skaypal.ebay_clone.domain.item.service;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.category.exceptions.CategoryNotFoundException;
import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.category.service.CategoryService;
import com.skaypal.ebay_clone.domain.item.dto.*;
import com.skaypal.ebay_clone.domain.item.exceptions.ItemNotFoundException;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.model.ItemImage;
import com.skaypal.ebay_clone.domain.item.repositories.item.ItemRepository;
import com.skaypal.ebay_clone.domain.item.repositories.item_image.ItemImageRepository;
import com.skaypal.ebay_clone.domain.item.repositories.queries.Filter;
import com.skaypal.ebay_clone.domain.item.validator.ItemValidator;
import com.skaypal.ebay_clone.properties.ImageStorageProperty;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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

    private final ItemImageRepository itemImageRepository;

    private final Path imageStoragePath;
    private final Integer ITEM_PAGE_SIZE = 4;

    @Autowired
    public ItemService(ItemRepository itemRepository,
                       ItemValidator itemValidator,
                       CategoryService categoryService,
                       ItemImageRepository itemImageRepository,
                       ImageStorageProperty imageStorageProperty) throws IOException {

        this.itemRepository = itemRepository;
        this.itemValidator = itemValidator;
        this.categoryService = categoryService;
        this.itemImageRepository = itemImageRepository;
        this.imageStoragePath = Paths.get(imageStorageProperty.getUploadDirectory()).toAbsolutePath().normalize();

        FileUtils.deleteDirectory(new File(imageStorageProperty.getUploadDirectory()));
        Files.createDirectories(imageStoragePath);
    }

    public List<ViewItemDto> getItems() {
        return itemRepository.findAll().stream().map((i) -> new ViewItemDto(i)).collect(Collectors.toList());
    }

    private Path resolveItemImageDirectoryPath(Integer id){
        String relativePathStr = String.format("item_%s", id.toString());
        return imageStoragePath.resolve(relativePathStr);

    }

    public ViewItemDto getItem(Integer id) {

        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("id", id.toString()));
        itemValidator.auctionIsEligibleForBids(id);
        ViewItemDto viewItemDto = new ViewItemDto(item);
        initializeDependedFields(viewItemDto);

        return viewItemDto;
    }


    private Item saveItem(CreateItemDto createItemDto) {
        Item item = new Item(createItemDto);

        List<String> categoryNames = createItemDto.getCategories();

        setItemCategories(item, categoryNames);

        itemRepository.save(item);

        return item;

    }

    public ViewItemDto createItem(CreateItemDto createItemDto) throws IOException {

        Item item = saveItem(createItemDto);

        List<MultipartFile> images = createItemDto.getImages();

        Path directoryPath = resolveItemImageDirectoryPath(item.getId());


        saveItemImages(item.getId(),directoryPath, images);

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
        else {
            List<Filter> filters = filtersDto.getFilters().stream().collect(Collectors.toCollection(ArrayList::new));
            itemPage = itemRepository.findAll(filters, PageRequest.of(p, ITEM_PAGE_SIZE));
        }
        itemPage.forEach((i) -> itemValidator.auctionIsEligibleForBids(i.getId()));
        Page<ViewItemDto> viewItemDtoPage = itemPage.map(item -> new ViewItemDto(item));
        viewItemDtoPage.forEach(i -> initializeDependedFields(i));
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

    private List<ItemImageDto> getItemImages(Integer id) {
        try {
            Path itemImageDirectoryPath = resolveItemImageDirectoryPath(id);
            if (Files.isDirectory(itemImageDirectoryPath) == false) return new ArrayList<>();

            List<ItemImage> images = itemImageRepository.findByItem(id);

            List<ItemImageDto> imageDtos = new ArrayList<>();

            for (ItemImage imageData : images) {
                String imageName = imageData.getName();
                Path imagePath = itemImageDirectoryPath.resolve(imageName);
                byte[] content = Base64.encodeBase64(Files.readAllBytes(imagePath));
                String contentType = imageData.getContentType();

                imageDtos.add(new ItemImageDto(imageName, content, contentType));

            }

            return imageDtos;
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }




    private void setItemCategories(Item item, List<String> categoryNames) {

        for (String str : categoryNames) {
            Optional<Category> category = categoryService.getCategory(str);
            if (category.isEmpty()) throw new CategoryNotFoundException(str);
            item.addCategory(category.get());
        }

    }

    private void saveItemImages(Integer itemId, Path directoryPath, List<MultipartFile> images) throws IOException {
        if (images == null) return;

        if (!Files.isDirectory(directoryPath)) Files.createDirectories(directoryPath);

        for (MultipartFile image : images) saveItemImage(itemId,directoryPath, image);
    }

    private void saveItemImage(Integer itemId,Path directoryPath, MultipartFile image) throws IOException {
        ItemImage itemImage = new ItemImage();
        itemImage.setName(image.getOriginalFilename());
        itemImage.setContentType(image.getContentType());
        itemImage.setItem(new Item(itemId));

        itemImageRepository.save(itemImage);

        Path imageStoragePath = directoryPath.resolve(itemImage.getName());

        Files.copy(image.getInputStream(), imageStoragePath);
    }

    private void initializeDependedFields(ViewItemDto viewItemDto){

        List<ItemImageDto> images = getItemImages(viewItemDto.getId());
        setBidData(viewItemDto);
        viewItemDto.setImages(images);

    }
}
