package com.arthur.ecommerceapi.customers.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.AddressMapper;
import com.arthur.ecommerceapi.customers.dtos.request.AddressPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.AddressRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.AddressResponseDTO;
import com.arthur.ecommerceapi.customers.usecases.CreateAddress;
import com.arthur.ecommerceapi.customers.usecases.FindAddress;
import com.arthur.ecommerceapi.customers.usecases.UpdateAddress;
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
    private final FindAddress findAddress;

    @ResponseStatus(OK)
    @PostMapping
    public AddressResponseDTO create(@RequestBody @Valid final AddressRequestDTO dto) {
        final var address = mapper.toDomain(dto);
        return mapper.toDTO(createAddress.create(address , dto.customerId()));
    }

    @ResponseStatus(OK)
    @PutMapping("/{id}")
    public AddressResponseDTO update(@PathVariable final Long id ,
                                     @RequestBody @Valid final AddressPutRequestDTO dto) {
        return mapper.toDTO(updateAddress.update(mapper.updateFromDTO(dto , id)));
    }

    @ResponseStatus(OK)
    @GetMapping("{id}")
    public AddressResponseDTO findById(@PathVariable final Long id) {
        return mapper.toDTO(findAddress.findById(id));
    }

}
