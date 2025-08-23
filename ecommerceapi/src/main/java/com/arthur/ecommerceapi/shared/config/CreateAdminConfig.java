package com.arthur.ecommerceapi.shared.config;

import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.customers.domain.model.RoleValues;
import com.arthur.ecommerceapi.customers.gateways.mappers.CustomerGatewayMapper;
import com.arthur.ecommerceapi.customers.repositories.CustomerRepository;
import com.arthur.ecommerceapi.roles.usecases.FindRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class CreateAdminConfig implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final FindRole findRole;
    private final CustomerGatewayMapper mapper;

    public CreateAdminConfig(CustomerRepository customerRepository, PasswordEncoder passwordEncoder, FindRole findRole, CustomerGatewayMapper mapper) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
        this.findRole = findRole;
        this.mapper = mapper;
    }

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

        var adminCustomer = new Customer();
        adminCustomer.setName("admin");
        adminCustomer.setEmail(adminEmail);
        adminCustomer.setPassword(passwordEncoder.encode(adminPassword));
        adminCustomer.setPhone("999-999-999");
        var customerRole = findRole.findRole(RoleValues.ADMIN);
        adminCustomer.addRole(customerRole);

        customerRepository.saveAndFlush(mapper.customerToEntity(adminCustomer));
    }
}
