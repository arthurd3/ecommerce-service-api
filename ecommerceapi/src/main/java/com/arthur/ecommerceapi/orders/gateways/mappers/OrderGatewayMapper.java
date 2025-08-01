package com.arthur.ecommerceapi.orders.gateways.mappers;

import com.arthur.ecommerceapi.orders.domain.model.Order;
import com.arthur.ecommerceapi.orders.gateways.entities.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface OrderGatewayMapper {

    @Mapping(source = "product.id", target = "productId")  
    @Mapping(source = "toAddress.id", target = "addressId")
    @Mapping(source = "customer.id", target = "customerId")
    @Mapping(source = "orderId", target = "id")
    OrderEntity toEntity(Order order);

}
