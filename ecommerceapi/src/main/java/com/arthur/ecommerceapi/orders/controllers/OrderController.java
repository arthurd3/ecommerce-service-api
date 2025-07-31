package com.arthur.ecommerceapi.orders.controllers;

import com.arthur.ecommerceapi.orders.dtos.request.OrderRequestDTO;
import com.arthur.ecommerceapi.orders.dtos.response.OrderResponseDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @ResponseStatus(OK)
    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid final OrderRequestDTO dto){
        return null;
    }
}
