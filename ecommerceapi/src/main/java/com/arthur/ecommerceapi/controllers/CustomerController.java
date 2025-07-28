package com.arthur.ecommerceapi.controllers;

import com.arthur.ecommerceapi.controllers.mapper.CustomerMapper;
import com.arthur.ecommerceapi.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.dtos.response.CustomerResponseDTO;
import com.arthur.ecommerceapi.usecases.CreateCustomer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMapper mapper;
    private final CreateCustomer create;

    @ResponseStatus(CREATED)
    @PostMapping
    public CustomerResponseDTO save(@RequestBody @Valid final CustomerRequestDTO dto){
        final var customer = mapper.toDomain(dto);
        return mapper.toDTO(create.createCustomer(customer));
    }
}
