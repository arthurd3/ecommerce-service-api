package com.arthur.ecommerceapi.orders.dtos.response;

public record AddressResponseDTO(
        Long addressId,
        String street,
        String city,
        String state,
        String zip,
        String country
) {}
