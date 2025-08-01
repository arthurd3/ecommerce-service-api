package com.arthur.ecommerceapi.orders.gateways.entities;

import com.arthur.ecommerceapi.customers.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.customers.gateways.entities.CustomerEntity;
import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import com.arthur.ecommerceapi.products.gateways.entities.ProductEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String specification;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private AddressEntity toAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    private OrderEntity(final ProductEntity product, final CustomerEntity customer, final AddressEntity address, final String specification , final OrderStatus status) {
        if (product == null || customer == null || address == null) {
            throw new IllegalArgumentException("Product and customer or address cannot be null.");
        }
        this.product = product;
        this.customer = customer;
        this.toAddress = address;
        this.specification = specification;
        this.status = status;
    }

    public static OrderEntity createObj(final ProductEntity product, final CustomerEntity customer, final AddressEntity address, final String specification) {
        return new OrderEntity( product, customer, address, specification , OrderStatus.PENDING_PAYMENT);
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

    public AddressEntity getToAddress(AddressEntity address) {
        final Long originalAddress = this.toAddress.getId();

        if (address == null || !address.getId().equals(originalAddress)) {
            throw new RuntimeException("Address is not valid");
        }

        return this.toAddress;
    }
}
