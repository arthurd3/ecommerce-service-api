package com.arthur.ecommerceapi.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.gateways.AddressGateway;
import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
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
        var savedAddress = repository.save(mapper.toEntity(address));
        return mapper.toDomain(savedAddress);
    }

    @Override
    public Boolean existsByCustomerId(final Long customerId) {
        return repository.existsByCustomer_Id(customerId);
    }

    @Override
    public Address findById(final Long addressId) {
        var updateAddress = repository.findById(addressId)
                .orElseThrow(() -> new UserNotFoundException("Addres with id :" +addressId + " not found!"));
        return mapper.toDomain(updateAddress);
    }

    @Override
    public Address update(final Address addressWithChanges) {
        AddressEntity entityToUpdate = repository.findById(addressWithChanges.getId())
                .orElseThrow(() -> new UserNotFoundException("Addres with id :" + addressWithChanges.getId() + " not found!"));

        mapper.updateEntityFromDomain(addressWithChanges, entityToUpdate);

        return mapper.toDomain(repository.save(entityToUpdate));
    }

}
