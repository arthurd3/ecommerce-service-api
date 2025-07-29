package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCustomer {

    private final CustomerGateway customerGateway;

    public void delete(final Long id){
        customerGateway.delete(id);
    }

}
