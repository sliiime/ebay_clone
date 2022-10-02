package com.skaypal.ebay_clone.configuration;

import com.skaypal.ebay_clone.domain.admin.AdminFactory;
import com.skaypal.ebay_clone.domain.bid.dto.CreateBidDto;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.JPABidRepository;
import com.skaypal.ebay_clone.domain.bid.service.BidService;
import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.interaction.service.InteractionService;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.dto.CreateItemDto;
import com.skaypal.ebay_clone.domain.item.dto.ViewItemDto;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.item.JPAItemRepository;
import com.skaypal.ebay_clone.domain.item.service.ItemService;
import com.skaypal.ebay_clone.domain.recommendation.service.RecommendationService;
import com.skaypal.ebay_clone.domain.role.model.Role;
import com.skaypal.ebay_clone.domain.user.UserRegStatus;
import com.skaypal.ebay_clone.domain.user.dto.CreateUserDto;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.JPAUserRepository;
import com.skaypal.ebay_clone.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.util.Date;
import java.util.List;


//For db initialization and testing purposes
@Configuration
public class OnStartupConfiguration {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserService userService;

    @Autowired
    ItemService itemService;

    @Autowired
    BidService bidService;

    @Autowired
    InteractionService interactionService;

    @Autowired
    RecommendationService recommendationService;

    @Bean
    CommandLineRunner userRepoInit(JPAUserRepository JPAUserRepository) {
        return args -> {
            CreateUserDto user1 = (new CreateUserDto(
                    "User1",
                    "Password1",
                    "Kostis",
                    "Palamidas",
                    "Addr 1",
                    "hello1@world.com",
                    "123456789",
                    "69696969",
                    "Albania"
            ));

            CreateUserDto user2 = (new CreateUserDto(
                    "User2",
                    "Password2",
                    "Panos",
                    "Seiras",
                    "Addr 2",
                    "hello2@world.com",
                    "123456788",
                    "1231231231",
                    "France"
            ));

            CreateUserDto user3 = (new CreateUserDto(
                    "User3",
                    "Password3",
                    "Io",
                    "Chamo",
                    "Addr 3",
                    "hello3@world.com",
                    "123453288",
                    "8889001234",
                    "Greece"
            ));

            User admin = (AdminFactory.admin(
                    "admin",
                    encoder.encode("Backfrombarca6*"),
                    "admin",
                    "admin",
                    "Admin 1",
                    "admin@admin.com",
                    "000000000",
                    "6945455420",
                    new Country(153)
            )
            );

            userService.createUser(user1);
            userService.createUser(user2);
            userService.createUser(user3);
            JPAUserRepository.save(admin);

            userService.approveUser(1);
        };
    }

    @Bean
    CommandLineRunner itemRepoInit(JPAItemRepository JPAItemRepository) {
        return args -> {
            CreateItemDto item1 = (new CreateItemDto("Warmogs's",
                    10f,
                    5f,
                    "peri",
                    List.of("Technology", "Beauty & Personal Care", "Clothing, Shoes & Jewelry", "Books"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    41.51899817,
                    19.79700359,
                    1
            ));
            CreateItemDto item2 = (new CreateItemDto("Thornmail",
                    15f,
                    7f,
                    "perigrafh",
                    List.of("Home & Kitchen", "Toys & Games", "Sports & Outdoors", "Other"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),

                    -63.068615,
                    18.220554,
                    1
            ));
            CreateItemDto item3 = (new CreateItemDto("email",
                    20f,
                    8f,
                    "perigrafhsee",
                    List.of("Beauty & Personal Care", "Other", "Clothing, Shoes & Jewelry", "Books"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    -74.297333,
                    4.570868,
                    1
            ));
            CreateItemDto item4 = (new CreateItemDto("patsas",
                    25f,
                    10f,
                    "grafh",
                    List.of("Toys & Games"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(150)),

                    22.43400358,
                    38.89899915,
                    1
            ));
            CreateItemDto item5 = (new CreateItemDto("Amogus",
                    140f,
                    3f,
                    "periklees",
                    List.of("Technology", "Toys & Games"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(30)),
                    -78.17900362,
                    18.44299809,
                    1
            ));
            CreateItemDto item6 = (new CreateItemDto("Thumbemail",
                    20f,
                    6f,
                    "perigrafh",
                    List.of("Home & Kitchen"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10)),
                    50.16659135,
                    -14.26617186,

                    1
            ));
            CreateItemDto item7 = (new CreateItemDto("Thumbroller",
                    20f,
                    9f,
                    "grafh ths peri",
                    List.of("Home & Kitchen", "Beauty & Personal Care"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(1000)),
                    7.486002487,
                    5.532003041,

                    2
            ));
            CreateItemDto item8 = (new CreateItemDto("broller de france",
                    20f,
                    3f,
                    "en peasant de france",
                    List.of("Home & Kitchen", "Beauty & Personal Care", "Clothing, Shoes & Jewelry"),
                    new Date(),
                    Date.from(Instant.now().plusSeconds(500)),
                    25.48583,
                    42.733883,

                    2
            ));

            itemService.createItem(item1);
            itemService.createItem(item2);
            itemService.createItem(item3);
            itemService.createItem(item4);
            itemService.createItem(item5);
            itemService.createItem(item6);
            itemService.createItem(item7);
            itemService.createItem(item8);


        };

    }

    @Bean
    CommandLineRunner bidRepoInit(JPABidRepository JPABidRepository) {
        return args -> {
            CreateBidDto bid1 = (new CreateBidDto(
                    2,
                    3,
                    10f
            )

            );
            CreateBidDto bid2 = (new CreateBidDto(
                    2,
                    2,
                    13f
            )
            );
            CreateBidDto bid3 = (new CreateBidDto(
                    3,
                    2,
                    16f
            )
            );
            CreateBidDto bid4 = (new CreateBidDto(
                    3,
                    3,
                    17f
            ));

            bidService.createBid(bid1);
            bidService.createBid(bid2);
            bidService.createBid(bid3);
            bidService.createBid(bid4);



        };
    }

    @Bean
    CommandLineRunner recommendationsInit() {
        return args -> {
            Page<ViewItemDto> viewItemDtoPage = itemService.getPage(null, 0);

            interactionService.initializeInteractions(viewItemDtoPage, 1);
            interactionService.initializeInteractions(viewItemDtoPage, 2);
            interactionService.initializeInteractions(viewItemDtoPage, 3);

            interactionService.itemViewed(1, 6);
            interactionService.itemViewed(1, 7);
            interactionService.itemViewed(1, 3);
            interactionService.itemViewed(1, 1);

            interactionService.itemViewed(2, 1);
            interactionService.itemViewed(2, 8);


            interactionService.itemViewed(3, 5);


            recommendationService.getRecommendations(2);

        };
    }
}
