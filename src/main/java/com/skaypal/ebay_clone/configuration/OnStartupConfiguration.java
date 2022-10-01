package com.skaypal.ebay_clone.configuration;

import com.skaypal.ebay_clone.domain.admin.AdminFactory;
import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.JPABidRepository;
import com.skaypal.ebay_clone.domain.category.model.Category;
import com.skaypal.ebay_clone.domain.country.model.Country;
import com.skaypal.ebay_clone.domain.interaction.model.Interaction;
import com.skaypal.ebay_clone.domain.interaction.service.InteractionService;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
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
    InteractionService interactionService;

    @Autowired
    RecommendationService recommendationService;
    @Bean
    CommandLineRunner userRepoInit(JPAUserRepository JPAUserRepository) {
        return args -> {
            CreateUserDto user1 = (new CreateUserDto(
                    "Bratsaras420",
                     "Psemouto4",
                    "Kostis",
                    "Palamidas",
                    "Spiti sou 3",
                    "kke@youjizz.com",
                    "123456789",
                    "69696969",
                    "Greece"
            ));

            CreateUserDto user2 = (new CreateUserDto(
                    "Boubounis666",
                     "TzouraApoTzina",
                    "Kyrios",
                    "Eugenios",
                    "Lilipoupoli 2",
                    "syriza@sugarbabes.com",
                    "123456788",
                    "12121312",
                    "Albania"
            ));

            CreateUserDto user3 = (new CreateUserDto(
                    "Damianoskow",
                    "Polovskis6",
                    "Karpos",
                    "Emiratwn",
                    "Panep 5",
                    "salamanca@hector.com",
                    "123453288",
                    "8889001234",
                    "Albania"
            ));

            User admin = ( AdminFactory.admin(
                    "Katsikas",
                    encoder.encode("Backfrombarca6*"),
                    "Panais",
                    "Palamidis",
                    "Negroniou 7",
                    "koulourades@yahoo.pub",
                    "778994662",
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
            Item item1 = (new Item("Warmogs's",
                    10f,
                    5f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    "peri",
                    List.of(new Category(1),new Category(3),new Category(5),new Category(7)),
                    ItemStatusEnum.ONGOING,
                    new User(1),
                    null,
                    new Country(123)
            ));
            Item item2 = (new Item("Thornmail",
                    15f,
                    7f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    "perigrafh",
                    List.of(new Category(2),new Category(4),new Category(6),new Category(8)),
                    ItemStatusEnum.ONGOING,
                    new User(1),
                    null,
                    new Country(99)
            ));
            Item item3 = (new Item("email",
                    20f,
                    8f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    "perigrafhsee",
                    List.of(new Category(3),new Category(8),new Category(5),new Category(7)),
                    ItemStatusEnum.ONGOING,
                    new User(1),
                    null,
                    new Country(6)
            ));
            Item item4 = (new Item("moggolos",
                    25f,
                    10f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(150)),
                    "grafh",
                    List.of(new Category(4)),
                    ItemStatusEnum.BOUGHT_BUYOUT,
                    new User(1),
                    new User(2),
                    new Country(4)
            ));
            Item item5 = (new Item("Amogus",
                    140f,
                    3f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(30)),
                    "periklees",
                    List.of(new Category(1),new Category(4)),
                    ItemStatusEnum.BOUGHT_TIMEOUT,
                    new User(1),
                    new User(2),
                    new Country(5)
            ));
            Item item6 = (new Item("Thumbemail",
                    20f,
                    6f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10)),
                    "perigrafh",
                    List.of(new Category(2)),
                    ItemStatusEnum.ONGOING,
                    new User(1),
                    null,
                    new Country(1)
            ));
            Item item7 = (new Item("Thumbroller",
                    20f,
                    9f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(1000)),
                    "grafh ths peri",
                    List.of(new Category(2),new Category(3)),
                    ItemStatusEnum.ONGOING,
                    new User(2),
                    null,
                    new Country(1)
            ));
            Item item8 = (new Item("broller de france",
                    20f,
                    3f,
                    80d,
                    95.4d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(500)),
                    "en peasant de france",
                    List.of(new Category(2),new Category(3),new Category(5)),
                    ItemStatusEnum.ONGOING,
                    new User(2),
                    null,
                    new Country(3)
            ));

            JPAItemRepository.saveAll(List.of(item1,item2,item3,item4,item5,item6,item7,item8));
        };

    }

    @Bean
    CommandLineRunner bidRepoInit(JPABidRepository JPABidRepository) {
        return args -> {
            Bid bid1 = (new Bid(
                    new Date(),
                    5f,
                    new Item(2),
                    new User(3))
            );
            Bid bid2 = (new Bid(
                    new Date(),
                    20f,
                    new Item(2),
                    new User(2))
            );
            Bid bid3 = (new Bid(
                    new Date(),
                    15f,
                    new Item(3),
                    new User(2)
            ));
            Bid bid4 = (new Bid(
                    new Date(),
                    17f,
                    new Item(3),
                    new User(3)
            ));


            JPABidRepository.saveAll(List.of(bid1,bid2,bid3,bid4));
        };
    }

    @Bean
    CommandLineRunner recommendationsInit(){
        return args -> {
            Page<ViewItemDto> viewItemDtoPage = itemService.getPage(null,0);

            interactionService.initializeInteractions(viewItemDtoPage,1);
            interactionService.initializeInteractions(viewItemDtoPage,2);
            interactionService.initializeInteractions(viewItemDtoPage,3);

            interactionService.itemViewed(1,6);
            interactionService.itemViewed(1,7);
            interactionService.itemViewed(1,3);
            interactionService.itemViewed(1,1);

            interactionService.itemViewed(2,1);
            interactionService.itemViewed(2,8);


            interactionService.itemViewed(3,5);


            recommendationService.getRecommendations(2);

        };
    }
}
