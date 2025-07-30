package com.arthur.ecommerceapi.controllers;

import com.arthur.ecommerceapi.controllers.mapper.AddressMapper;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.dtos.response.AddressResponseDTO;
import com.arthur.ecommerceapi.usecases.CreateAddress;
import com.arthur.ecommerceapi.usecases.FindAddress;
import com.arthur.ecommerceapi.usecases.FindCustomer;
import com.arthur.ecommerceapi.usecases.UpdateAddress;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/customer/address")
@RequiredArgsConstructor
public class CustomerAddressController {

    private final CreateAddress createAddress;
    private final AddressMapper mapper;
    private final UpdateAddress updateAddress;

    @ResponseStatus(OK)
    @PostMapping
    public AddressResponseDTO create(@RequestBody @Valid final AddressRequestDTO dto) {
        final var address = mapper.toDomain(dto);
        final Long customerId = dto.customerId();
        return mapper.toDTO(createAddress.create(address , customerId));
    }

    @ResponseStatus(OK)
    @PutMapping
    public AddressResponseDTO update(@RequestBody @Valid final AddressPutRequestDTO dto) {
        final var updatedDomainAddress = mapper.updateFromDTO(dto);
        return mapper.toDTO(updateAddress.update(updatedDomainAddress));
    }

}
