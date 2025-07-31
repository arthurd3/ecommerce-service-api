package com.arthur.ecommerceapi.customer.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customer.domain.model.Customer;
import com.arthur.ecommerceapi.customer.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.customer.gateways.CustomerGateway;
import com.arthur.ecommerceapi.customer.gateways.mappers.GatewayMapper;
import com.arthur.ecommerceapi.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CustomerGatewayImpl implements CustomerGateway {

    private final CustomerRepository repository;
    private final GatewayMapper mapper;

    @Override
    public Customer save(final Customer customer) {
        return mapper.customerToDomain(repository.save(mapper.customerToEntity(customer)));
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
        return repository.findAll(pageable).map(mapper::customerToDomain);
    }

    @Override
    public Customer findById(final Long id) {
        return mapper.customerToDomain(repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id)));
    }

    @Override
    public void delete(final Long id) {
        repository.deleteById(id);
    }

    @Override
    public Boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public Customer update(Customer updatedCustomer) {
        return null;
    }


}
