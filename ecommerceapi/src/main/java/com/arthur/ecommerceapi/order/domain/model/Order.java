package com.arthur.ecommerceapi.order.domain.model;

import com.arthur.ecommerceapi.customer.domain.model.Address;
import com.arthur.ecommerceapi.customer.domain.model.Customer;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Order {
    private UUID orderId;
//    private Product product;
    private String specification;
    private Customer customer;
    private Address toAddress;
}
