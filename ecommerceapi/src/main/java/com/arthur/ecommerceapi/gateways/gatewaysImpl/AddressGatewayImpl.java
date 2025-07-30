package com.arthur.ecommerceapi.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.gateways.AddressGateway;
import com.arthur.ecommerceapi.gateways.mappers.AddressGatewayMapper;
import com.arthur.ecommerceapi.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressGatewayImpl implements AddressGateway {

    private final AddressRepository repository;
    private final AddressGatewayMapper mapper;

    @Override
    public Address save(final Address address) {
        return mapper.toDomain(repository.save(mapper.toEntity(address)));
    }

    @Override
    public Boolean existsByCustomerId(final Long customerId) {
        return repository.existsByCustomer_Id(customerId);
    }

}
