package com.arthur.ecommerceapi.orders.dtos.response;

public record CustomerResponseDTO(
        Long customerId,
        String nome ,
        String cpf ,
        String email ,
        String phone
) {}
