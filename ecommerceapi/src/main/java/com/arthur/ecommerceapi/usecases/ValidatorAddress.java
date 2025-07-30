package com.arthur.ecommerceapi.usecases;

import com.arthur.ecommerceapi.gateways.AddressGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidatorAddress {

    private final AddressGateway addressGateway;


}
