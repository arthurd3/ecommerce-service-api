package com.arthur.ecommerceapi.orders.dtos.response;

public record CustomerOderResponseDTO(
        Long customerId,
        String name ,
        String email ,
        String phone
) {}
