package com.arthur.ecommerceapi.orders.dtos.response;

public record CustomerResponseDTO(
        Long customerId,
        String name ,
        String email ,
        String phone
) {}
