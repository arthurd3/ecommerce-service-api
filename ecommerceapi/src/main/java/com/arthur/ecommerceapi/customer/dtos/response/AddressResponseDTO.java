package com.arthur.ecommerceapi.customer.dtos.response;

public record AddressResponseDTO(
        Long id ,
        String street,
        String city,
        String state ,
        String zip ,
        String country
) {}
