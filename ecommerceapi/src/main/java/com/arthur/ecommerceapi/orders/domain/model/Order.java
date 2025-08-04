package com.arthur.ecommerceapi.orders.domain.model;

import com.arthur.ecommerceapi.customers.domain.model.Address;
import com.arthur.ecommerceapi.customers.domain.model.Customer;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
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

    private Order(UUID orderId, Product product, Customer customer, Address address, String specification , OrderStatus status) {
        if (product == null || customer == null || address == null) {
            throw new IllegalArgumentException("Product and customer or address cannot be null.");
        }
        this.orderId = orderId;
        this.product = product;
        this.customer = customer;
        this.toAddress = address;
        this.specification = specification;
        this.status = status;
    }

    public static Order createOrder(OrderEntity order , Product product, Customer customer, Address address) {
        return new Order (
                order.getId(),
                product,
                customer,
                address,
                order.getSpecification(),
                order.getStatus()
        );
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

    public Address getAddress(Address address) {
        final Long originalAddress = this.toAddress.getId();

        if (address == null || !address.getId().equals(originalAddress)) {
            throw new RuntimeException("Address is not valid");
        }

        return this.toAddress;
    }
}
