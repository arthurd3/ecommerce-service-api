package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.dtos.request.CustomerRequest;
import com.arthur.ecommerceapi.dtos.response.CustomerResponse;
import com.arthur.ecommerceapi.gateways.CustomerGateway;
import com.arthur.ecommerceapi.gateways.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCustomer {

    private final CustomerGateway customerGateway;
    private final CustomerMapper customerMapper;


    public CustomerResponse createCustomer(final CustomerRequest dto) {
         var returnObj = customerMapper.toDomain(dto);
         return customerMapper.toResponse(returnObj);
    }
}
