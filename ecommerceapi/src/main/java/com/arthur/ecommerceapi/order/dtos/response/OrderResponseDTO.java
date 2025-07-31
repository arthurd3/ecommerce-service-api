package com.arthur.ecommerceapi.order.dtos.response;

import com.arthur.ecommerceapi.customer.domain.model.Address;

import java.math.BigInteger;
import java.util.UUID;

public record OrderResponseDTO (UUID id ,
                                String customerName ,
                                Address toAddress ,
                                String specification ,
                                BigInteger price
) {}
