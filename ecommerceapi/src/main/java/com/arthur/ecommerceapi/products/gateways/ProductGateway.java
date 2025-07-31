package com.arthur.ecommerceapi.products.gateways;

import com.arthur.ecommerceapi.products.domain.models.Product;

public interface ProductGateway {

    Product create(Product product);

}
