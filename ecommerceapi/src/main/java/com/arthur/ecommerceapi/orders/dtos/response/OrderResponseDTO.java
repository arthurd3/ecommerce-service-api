package com.arthur.ecommerceapi.orders.dtos.response;

import com.arthur.ecommerceapi.customers.domain.model.Address;

import java.math.BigInteger;
import java.util.UUID;

public record OrderResponseDTO (UUID id ,
                                String customerName ,
                                Address toAddress ,
                                String specification ,
                                BigInteger price
) {}
