package com.arthur.ecommerceapi.orders.gateways.entities;

import com.arthur.ecommerceapi.orders.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String specification;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @CreationTimestamp
    @Column(name = "order_date", updatable = false)
    private Instant orderDate;

    @Column(name = "product_id")
    private UUID productId;
    @Column(name = "customer_id")
    private Long customerId;
    @Column(name = "address_id")
    private Long addressId;
}
