package com.arthur.ecommerceapi.customers.dtos.request;

public record CustomerPutRequestDTO (
        String name,

        String email ,

        String phone
){}
