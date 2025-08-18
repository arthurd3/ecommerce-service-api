package com.arthur.ecommerceapi.products.controllers.mappers;

import com.arthur.ecommerceapi.products.domain.models.Money;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(componentModel = SPRING , unmappedTargetPolicy = IGNORE)
public interface MoneyMapper {

    Money decimalToMoney(BigDecimal decimal);
}
