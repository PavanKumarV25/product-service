package com.tech.productservice.service;

import com.tech.productservice.model.ProductRequest;
import com.tech.productservice.model.ProductResponse;

public interface ProductService {
    long addProduct(ProductRequest prequest);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(long productId, long quantity);
}
