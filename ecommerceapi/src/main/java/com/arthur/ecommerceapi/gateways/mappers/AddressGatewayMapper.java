package com.arthur.ecommerceapi.gateways.mappers;

import com.arthur.ecommerceapi.domain.model.Address;
import com.arthur.ecommerceapi.domain.model.Customer;
import com.arthur.ecommerceapi.gateways.entities.AddressEntity;
import com.arthur.ecommerceapi.gateways.entities.CustomerEntity;
import org.mapstruct.*;

import static org.mapstruct.ReportingPolicy.IGNORE;


@Mapper(componentModel = "spring", unmappedTargetPolicy = IGNORE)
public interface AddressGatewayMapper {

    // --- Caminho de Leitura (Entity -> Domain) ---

    Address toDomain(AddressEntity entity);

    @Mapping(target = "address", ignore = true) // Quebra o ciclo na leitura
    Customer customerEntityToCustomer(CustomerEntity customerEntity);


    // --- Caminho de Escrita (Domain -> Entity) ---

    AddressEntity toEntity(Address address);

    // ADICIONE ESTE MÉTODO PARA QUEBRAR O CICLO NA ESCRITA
    @Mapping(target = "address", ignore = true) // Quebra o ciclo na escrita
    CustomerEntity customerToCustomerEntity(Customer customer);
}
