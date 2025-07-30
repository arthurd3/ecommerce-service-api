package com.arthur.ecommerceapi.controllers;

import com.arthur.ecommerceapi.controllers.mapper.AddressMapper;
import com.arthur.ecommerceapi.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.dtos.response.AddressResponseDTO;
import com.arthur.ecommerceapi.usecases.CreateAddress;
import com.arthur.ecommerceapi.usecases.FindCustomer;
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

    @ResponseStatus(OK)
    @PostMapping
    public AddressResponseDTO create(@RequestBody @Valid final AddressRequestDTO addressRequestDTO) {
        final var address = mapper.toDomain(addressRequestDTO);
        final Long customerId = addressRequestDTO.customerId();
        return mapper.toDTO(createAddress.create(address , customerId));
    }

}
