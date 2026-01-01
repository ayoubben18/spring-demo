package com.crud.demo.config;

import com.crud.demo.model.User;
import com.crud.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args){
        if (userRepository.findByUsername("admin").isEmpty()){
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password123"));
            admin.setRole("ROLE_ADMIN");

            User customer = new User();
            customer.setUsername("customer");
            customer.setPassword(passwordEncoder.encode("password123"));
            customer.setRole("ROLE_CUSTOMER");

            userRepository.saveAll(List.of(admin, customer));

            IO.println("Test user created: admin/password123");
            IO.println("Test customer created: customer/password123");
        }
    }
}
