package com.tech.productservice.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tech.productservice.entity.Product;
import com.tech.productservice.exeption.ProductServiceCustomException;
import com.tech.productservice.model.ProductRequest;
import com.tech.productservice.model.ProductResponse;
import com.tech.productservice.repository.ProductRepository;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository pRepo;
    @Override
    public long addProduct(ProductRequest prequest) {
        log.info("Adding Product");
        Product product= Product.builder()
                .productName(prequest.getProductName())
                .price(prequest.getPrice())
                .quantity(prequest.getQuantity())
                .build();
//        ObjectMapper om=new ObjectMapper();
//        Product product1=om.convertValue(prequest,Product.class);
         pRepo.save(product);
         return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        ProductResponse presponse=new ProductResponse();
        Product product=pRepo.findById(productId).orElseThrow(()->new ProductServiceCustomException("product with given ID not found","PRODUCT_NOT_FOUND"));
        BeanUtils.copyProperties(product,presponse);
        return presponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce Quantity {} for Id {}",quantity,productId);
        Product product=pRepo.findById(productId).orElseThrow(()->
                new ProductServiceCustomException("product with given Id not found","PRODUCT_NOT_FOUND"));

        if(product.getQuantity()<quantity)
        {
            throw new ProductServiceCustomException("Product does not have sufficient Quantity","Insufficient Quantity");
        }
        product.setQuantity(product.getQuantity()-quantity);
        pRepo.save(product);
        log.info("Product Quantity Updated Succesfully");
    }
}
