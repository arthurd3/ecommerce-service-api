package com.arthur.ecommerceapi.shared.config;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomMetadata;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class AdminConfig implements CommandLineRunner {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;

    @Value("${admin.password}")
    private String adminPassword;

    @Value("${admin.email}")
    private String adminEmail;

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        if (customerRepository.findByEmail(adminEmail).isPresent()) {
            System.out.println("Admin already exists");
            return;
        }

        var adminCustomer = new CustomerEntity();
        adminCustomer.setName("admin");
        adminCustomer.setEmail(adminEmail);
        adminCustomer.setPassword(passwordEncoder.encode(adminPassword));
        adminCustomer.setPhone("999-999-999");
        adminCustomer.setAddress(new AddressEntity());
        adminCustomer.setCustomMetadata(new CustomMetadata(List.of("ROLE_USER" , "ROLE_ADMIN") , List.of("EDIT")));
        customerRepository.saveAndFlush(adminCustomer);

    }
}
