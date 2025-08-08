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
@RequestMapping("/api/v1/address/customer")
@RequiredArgsConstructor
public class CustomerAddressController {

    private final CreateAddress createAddress;
    private final AddressMapper mapper;
    private final UpdateAddress updateAddress;
    private final FindAddress findAddress;

    @ResponseStatus(OK)
    @PostMapping("{id}")
    public AddressResponseDTO create(@PathVariable(name = "id") final Long customerId ,
                                     @RequestBody @Valid final AddressRequestDTO dto) {
        final var address = mapper.toDomain(dto , customerId);
        return mapper.toDTO(createAddress.create(address));
    }

    @ResponseStatus(OK)
    @PutMapping("{id}")
    public AddressResponseDTO update(@PathVariable(name = "id") final Long customerId ,
                                     @RequestBody @Valid final AddressPutRequestDTO dto) {
        final var addressChanged = mapper.updateFromDTO(dto , customerId);
        return mapper.toDTO(updateAddress.update(addressChanged));
    }

    @ResponseStatus(OK)
    @GetMapping("/findAddress/{id}")
    public AddressResponseDTO findById(@PathVariable final Long id) {
        return mapper.toDTO(findAddress.findById(id));
    }

}
