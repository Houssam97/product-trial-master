package com.alten.service.impl;

import com.alten.models.User;
import com.alten.repository.UserRepository;
import com.alten.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        if (userRepository.findByEmail("admin@admin.com").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setFirstname("Admin");
            admin.setPassword("admin123");
            admin.setEmail("admin@admin.com");

            userRepository.save(admin);
            log.info("Admin user created: admin@admin.com / admin123");
        }
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }
}
