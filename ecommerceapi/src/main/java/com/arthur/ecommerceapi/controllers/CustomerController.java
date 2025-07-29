package com.arthur.ecommerceapi.controllers;

import com.arthur.ecommerceapi.controllers.mapper.CustomerMapper;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.dtos.response.CustomerResponseDTO;
import com.arthur.ecommerceapi.usecases.CreateCustomer;
import com.arthur.ecommerceapi.usecases.FindAllCustomer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMapper mapper;
    private final CreateCustomer create;
    private final FindAllCustomer findAll;

    @ResponseStatus(CREATED)
    @PostMapping
    public CustomerResponseDTO save(@RequestBody @Valid final CustomerRequestDTO dto){
        final var customer = mapper.toDomain(dto);
        return mapper.toDTO(create.execute(customer));
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<CustomerResponseDTO> findAll(@RequestParam final Integer page, @RequestParam final Integer size){
        final var pageRequest = PageRequest.of(page, size);
        return findAll.find(pageRequest).map(mapper::toDTO);
    }
}
