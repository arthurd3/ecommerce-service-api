package com.arthur.ecommerceapi.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import com.arthur.ecommerceapi.gateways.mappers.CustomerGatewayMapper;
import com.arthur.ecommerceapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository repository;
    private final CustomerGatewayMapper mapper;

    @Override
    public Customer save(Customer customer) {
        return mapper.toDomain(repository.save(mapper.toEntity(customer)));
    }
}
