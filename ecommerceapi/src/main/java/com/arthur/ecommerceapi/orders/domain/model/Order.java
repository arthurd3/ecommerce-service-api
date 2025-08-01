package com.arthur.ecommerceapi.orders.domain.model;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.products.domain.models.Product;
import lombok.Getter;

import java.util.UUID;

@Getter
public class Order {

    private final UUID orderId;
    private final Product product;
    private final Customer customer;
    private final Address toAddress;

    private String specification;
    private OrderStatus status;

    private Order(UUID orderId, Product product, Customer customer, Address address, String specification) {
        if (product == null || customer == null || address == null) {
            throw new IllegalArgumentException("Product and customer or address cannot be null.");
        }
        this.orderId = orderId;
        this.product = product;
        this.customer = customer;
        this.toAddress = address;
        this.specification = specification;
        this.status = OrderStatus.PENDING_PAYMENT;
    }

    public static Order createObj(Product product, Customer customer, Address address, String specification) {
        return new Order(UUID.randomUUID(), product, customer, address, specification);
    }

    public void approve() {
        if (this.status != OrderStatus.PENDING_PAYMENT) {
            throw new IllegalStateException("Pending Payment Order has been approved.");
        }
        this.status = OrderStatus.PAYMENT_CONFIRMED;
    }

    public void ship() {
        if (this.status != OrderStatus.PAYMENT_CONFIRMED) {
            throw new IllegalStateException("Only Payment Order has been approved.");
        }
        this.status = OrderStatus.SHIPPED;
    }

    public void cancel(String reason) {
        if (this.status == OrderStatus.DELIVERED || this.status == OrderStatus.CANCELED) {
            throw new IllegalStateException("It has been cancelled.");
        }
        this.status = OrderStatus.CANCELED;
    }

    public void updateSpecification(String newSpecification) {
        this.specification = newSpecification;
    }
}
