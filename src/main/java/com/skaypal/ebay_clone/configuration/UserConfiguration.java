package com.skaypal.ebay_clone.configuration;

import com.skaypal.ebay_clone.UserRegStatus;
import com.skaypal.ebay_clone.model.User;
import com.skaypal.ebay_clone.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository){
        return args -> {
            User user1= (new User(
                    "Bratsaras420",
                    "Psemouto4",
                    "Kostis",
                    "Palamidas",
                    "Spiti sou 3",
                    "kke@youjizz.com",
                    "afm",1F,
                    UserRegStatus.PENDING,
                    "69696969"));

            User user2 = (new User(
                    "Boubounokefalos666",
                    "TzouraApoTzina",
                    "Kyrios",
                    "Eugenios",
                    "Lilipoupoli 2",
                    "syriza@sugarbabes.com",
                    "afm",1F,
                    UserRegStatus.PENDING,
                    "12121312"));

            userRepository.saveAll(List.of(user1,user2));
        };
    }
}
