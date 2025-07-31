package com.arthur.ecommerceapi.order.controllers;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    @ResponseStatus(OK)
    @PostMapping
    public OrderResponseDTO create(@RequestBody @Valid final OrderRequestDTO dto){

    }
}
