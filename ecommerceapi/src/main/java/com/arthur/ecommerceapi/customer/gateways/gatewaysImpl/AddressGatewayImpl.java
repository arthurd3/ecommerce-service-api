package com.arthur.ecommerceapi.customer.gateways.gatewaysImpl;

import com.arthur.ecommerceapi.customer.domain.model.Address;
import com.arthur.ecommerceapi.customer.exceptions.AddressNotFound;
import com.arthur.ecommerceapi.customer.gateways.AddressGateway;
import com.arthur.ecommerceapi.customer.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customer.gateways.mappers.GatewayMapper;
import com.arthur.ecommerceapi.customer.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressGatewayImpl implements AddressGateway {

    private final AddressRepository repository;
    private final GatewayMapper mapper;

    @Override
    public Address save(final Address address) {
        var savedAddress = repository.save(mapper.addressToEntity(address));
        return mapper.addressToDomain(savedAddress);
    }

    @Override
    public Boolean existsByCustomerId(final Long customerId) {
        return repository.existsByCustomer_Id(customerId);
    }

    @Override
    public Address findById(final Long addressId) {
        var updateAddress = repository.findById(addressId)
                .orElseThrow(() -> new AddressNotFound("Addres with id :" +addressId + " not found!"));
        return mapper.addressToDomain(updateAddress);
    }

    @Override
    public Address update(final Address addressWithChanges) {
        AddressEntity entityToUpdate = repository.findById(addressWithChanges.getId())
                .orElseThrow(() -> new AddressNotFound("Addres with id :" + addressWithChanges.getId() + " not found!"));

        mapper.editEntityFromDomain(addressWithChanges, entityToUpdate);

        return mapper.addressToDomain(repository.save(entityToUpdate));
    }

}
