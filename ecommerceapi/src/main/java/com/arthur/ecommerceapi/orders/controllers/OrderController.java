package com.arthur.ecommerceapi.orders.controllers;

import com.arthur.ecommerceapi.orders.controllers.mapppers.OrderMapper;
import com.arthur.ecommerceapi.orders.dtos.request.OrderRequestDTO;
import com.arthur.ecommerceapi.orders.dtos.response.OrderResponseDTO;
import com.arthur.ecommerceapi.orders.usecases.CreateOrder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrder createOrder;
    private final OrderMapper mapper;

    @ResponseStatus(OK)
    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid final OrderRequestDTO dto){
        return mapper.toDto(createOrder.create(dto));
    }
}
