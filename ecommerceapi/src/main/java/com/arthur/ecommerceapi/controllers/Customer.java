package com.arthur.ecommerceapi.controllers;

import com.arthur.ecommerceapi.dtos.request.CustomerRequest;
import com.arthur.ecommerceapi.usecases.CreateCustomer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class Customer {

    private final CreateCustomer create;

    @ResponseStatus(CREATED)
    @PostMapping()
    public CustomerResponse save(@RequestBody @Valid final CustomerRequest dto){
        return create.createCustomer(dto);
    }


}
