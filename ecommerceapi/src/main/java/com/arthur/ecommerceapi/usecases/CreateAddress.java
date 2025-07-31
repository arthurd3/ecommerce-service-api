package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.exceptions.UserAlreadyHaveAddress;
import com.arthur.ecommerceapi.exceptions.UserNotFoundException;
import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.gateways.mappers.GatewayMapper;
import com.arthur.ecommerceapi.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateAddress {

    private final GatewayMapper addressMapper;
    private final CustomerRepository customerRepository;
    private final FindCustomer findCustomer;

    @Transactional
    public Address create(final Address address , final Long customerId) {

        CustomerEntity customerEntity = customerRepository.findById(customerId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + customerId));

        if (customerEntity.getAddress() != null) {
            throw new UserAlreadyHaveAddress("This User already has an address");
        }

        AddressEntity addressEntity = addressMapper.addressToEntity(address);

        customerEntity.defineAddress(addressEntity);

        CustomerEntity savedCustomer = customerRepository.save(customerEntity);

        return addressMapper.addressToDomain(savedCustomer.getAddress());
    }
}
