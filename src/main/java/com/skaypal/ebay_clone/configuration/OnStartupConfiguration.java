package com.skaypal.ebay_clone.configuration;

import com.skaypal.ebay_clone.domain.bid.model.Bid;
import com.skaypal.ebay_clone.domain.bid.repository.BidRepository;
import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.JPAItemRepository;
import com.skaypal.ebay_clone.domain.user.UserRegStatus;
import com.skaypal.ebay_clone.domain.user.model.User;
import com.skaypal.ebay_clone.domain.user.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Date;
import java.util.List;


//For db initialization and testing purposes
@Configuration
public class OnStartupConfiguration {

    @Bean
    CommandLineRunner userRepoInit(UserRepository userRepository) {
        return args -> {
            User user1 = (new User(
                    "Bratsaras420",
                    "Psemouto4",
                    "Kostis",
                    "Palamidas",
                    "Spiti sou 3",
                    "kke@youjizz.com",
                    "123456789", 1F,
                    UserRegStatus.PENDING,
                    "69696969"));

            User user2 = (new User(
                    "Boubounis666",
                    "TzouraApoTzina",
                    "Kyrios",
                    "Eugenios",
                    "Lilipoupoli 2",
                    "syriza@sugarbabes.com",
                    "123456788", 1F,
                    UserRegStatus.PENDING,
                    "12121312"));

            userRepository.saveAll(List.of(user1, user2));
        };
    }

    @Bean
    CommandLineRunner itemRepoInit(JPAItemRepository JPAItemRepository) {
        return args -> {
            Item item1 = (new Item("item1",
                    3f,
                    8f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    "perigrafh",
                    "KATHGORIA",
                    ItemStatusEnum.BOUGHT_BUYOUT,
                    new User(1)
            ));
            Item item2 = (new Item("item2",
                    1f,
                    1f,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    "perigrafh",
                    "KATHGORIA",
                    ItemStatusEnum.BOUGHT_BUYOUT,
                    new User(1)
            ));

            JPAItemRepository.saveAll(List.of(item1,item2));
        };
    }

    @Bean
    CommandLineRunner bidRepoInit(BidRepository bidRepository) {
        return args -> {
            Bid bid1 = (new Bid(
                    new Date(),
                    3f,
                    new Item(1),
                    new User(2))
            );
            Bid bid2 = (new Bid(
                    new Date(),
                    5f,
                    new Item(2),
                    new User(1))
            );

            bidRepository.saveAll(List.of(bid1,bid2));
        };
    }
}
