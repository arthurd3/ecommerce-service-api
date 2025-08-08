package com.arthur.ecommerceapi.orders.dtos.response;

import com.arthur.ecommerceapi.products.dtos.response.ProductResponseDTO;

import java.util.UUID;

public record OrderResponseDTO (UUID oderId ,
                                CustomerOderResponseDTO customer ,
                                AddressOrderResponseDTO toAddress ,
                                String specification ,
                                ProductResponseDTO product
) {}
