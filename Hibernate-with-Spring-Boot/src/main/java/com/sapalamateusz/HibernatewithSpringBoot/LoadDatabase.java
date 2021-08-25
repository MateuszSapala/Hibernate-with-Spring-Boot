package com.sapalamateusz.HibernatewithSpringBoot;

import com.sapalamateusz.HibernatewithSpringBoot.dao.UserRepository;
import com.sapalamateusz.HibernatewithSpringBoot.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoadDatabase {
    private static final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository){
        return args -> {
            userRepository.save(new User("admin123","administrator"));
            userRepository.save(new User("user123", "user"));
            userRepository.findAll().forEach(u -> logger.info("Preloaded "+u));
        };
    }
}

