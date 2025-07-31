package com.arthur.ecommerceapi.products.usecases;

import com.arthur.ecommerceapi.products.gateways.ProductGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeleteProduct {

    private final ProductGateway productGateway;

    public void delete(final UUID uuid) {
        if(productGateway.findById(uuid) != null)
            productGateway.delete(uuid);
    }
}
