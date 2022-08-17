package com.skaypal.ebay_clone.configuration;

import com.skaypal.ebay_clone.domain.item.ItemStatusEnum;
import com.skaypal.ebay_clone.domain.item.model.Item;
import com.skaypal.ebay_clone.domain.item.repositories.ItemRepository;
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
    CommandLineRunner userRepoInit(UserRepository userRepository){
        return args -> {
            User user1= (new User(
                    "Bratsaras420",
                    "Psemouto4",
                    "Kostis",
                    "Palamidas",
                    "Spiti sou 3",
                    "kke@youjizz.com",
                    "123456789",1F,
                    UserRegStatus.PENDING,
                    "69696969"));

            User user2 = (new User(
                    "Boubounis666",
                    "TzouraApoTzina",
                    "Kyrios",
                    "Eugenios",
                    "Lilipoupoli 2",
                    "syriza@sugarbabes.com",
                    "123456788",1F,
                    UserRegStatus.PENDING,
                    "12121312"));

            userRepository.saveAll(List.of(user1,user2));
        };
    }

    @Bean
    CommandLineRunner itemRepoInit(ItemRepository itemRepository){
        return args -> {
            Item item1= (new Item("item1",
                    3f,
                    10f,
                    8f,
                    30,
                    50d,
                    40d,
                    new Date(),
                    Date.from(Instant.now().plusSeconds(10000)),
                    "perigrafh",
                    "KATHGORIA",
                    ItemStatusEnum.BOUGHT_BUYOUT

                   ));

            itemRepository.save(item1);
        };
    }
}
