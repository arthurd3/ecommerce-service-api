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
    private final FindAddress findAddress;
    private final UpdateAddress updateAddress;
    private final AddressMapper mapper;
    private final FindCustomer findCustomer;

    @ResponseStatus(OK)
    @PostMapping
    public AddressResponseDTO create(@RequestBody @Valid final AddressRequestDTO dto) {
        final var address = mapper.toDomain(dto);
        final Long customerId = dto.customerId();
        return mapper.toDTO(createAddress.create(address , customerId));
    }

    @ResponseStatus(OK)
    @PutMapping("{id}")
    public AddressResponseDTO update(@PathVariable(name = "id") Long customerId ,
                                     @RequestBody @Valid final AddressPutRequestDTO dto) {
        final var addressUpdated = findAddress.findById(dto.addressId());
        final var addressToResponse = mapper.updateFromDTO(dto , addressUpdated);
        return mapper.toDTO(updateAddress.update(addressToResponse));
    }

}
