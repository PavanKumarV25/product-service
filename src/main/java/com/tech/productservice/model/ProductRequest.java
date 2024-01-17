package com.tech.productservice.model;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;
    private Long quantity;
    private Long price;
}
