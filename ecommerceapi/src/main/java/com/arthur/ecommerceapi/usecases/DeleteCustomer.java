package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.gateways.CustomerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCustomer {

    private final CustomerGateway customerGateway;
    private final CustomerValidator validator;

    @Transactional
    public void delete(final Long id){
        validator.validateExists(id);
        customerGateway.delete(id);
    }

}
