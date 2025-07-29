package com.arthur.ecommerceapi.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import com.arthur.ecommerceapi.gateways.mappers.CustomerGatewayMapper;
import com.arthur.ecommerceapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository repository;
    private final CustomerGatewayMapper mapper;

    @Override
    public Customer save(final Customer customer) {
        return mapper.toDomain(repository.save(mapper.toEntity(customer)));
    }

    @Override
    public Boolean existsByEmail(final String email) {
        return repository.existsByEmailIgnoreCase(email);
    }

    @Override
    public Boolean existsByPhone(final String phone) {
        return repository.existsByPhone(phone);
    }

    @Override
    public Page<Customer> findAll(final Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toDomain);
    }

    @Override
    public Customer findById(final Long id) {
        return mapper.toDomain(repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    @Override
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }


}
