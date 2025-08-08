package com.arthur.ecommerceapi.customers.controllers;

import com.arthur.ecommerceapi.customers.controllers.mappers.CustomerMapper;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerPutRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.request.CustomerRequestDTO;
import com.arthur.ecommerceapi.customers.dtos.response.CustomerResponseDTO;
import com.arthur.ecommerceapi.customers.usecases.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerMapper mapper;
    private final CreateCustomer createCustomer;
    private final FindCustomer findCustomer;
    private final DeleteCustomer deleteCustomer;
    private final FindAllCustomers findAllCustomers;
    private final UpdateCustomer updateCustomer;


    @ResponseStatus(CREATED)
    @PostMapping
    public CustomerResponseDTO save(@RequestBody @Valid final CustomerRequestDTO dto){
        final var customer = mapper.toDomain(dto);
        return mapper.toDTO(createCustomer.create(customer));
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<CustomerResponseDTO> findAll(@RequestParam @Min(0)final Integer page,
                                             @RequestParam @Min(1)final Integer size){
        final var pageRequest = PageRequest.of(page, size);
        return findAllCustomers.findAll(pageRequest).map(mapper::toDTO);
    }

    @ResponseStatus(OK)
    @GetMapping("{id}")
    public CustomerResponseDTO findById(@PathVariable final Long id){
        return mapper.toDTO(findCustomer.findById(id));
    }

    @ResponseStatus(NO_CONTENT)
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable final Long id){
        deleteCustomer.delete(id);
    }

    @ResponseStatus(OK)
    @PutMapping("{id}")
    public CustomerResponseDTO update(@PathVariable final Long id,
                                      @RequestBody @Valid final CustomerPutRequestDTO dto){
        return mapper.toDTO(updateCustomer.update(mapper.updateFromDTO(dto , id)));
    }

}
