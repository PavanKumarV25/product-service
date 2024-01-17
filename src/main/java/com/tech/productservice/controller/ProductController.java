package com.tech.productservice.controller;

import com.tech.productservice.model.ProductRequest;
import com.tech.productservice.model.ProductResponse;
import com.tech.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService pService;

    @PostMapping("/addProduct")
    public ResponseEntity<Long> addProduct(@RequestBody ProductRequest prequest)
    {
        long productId=pService.addProduct(prequest);
        return new ResponseEntity<>(productId,HttpStatus.CREATED);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") Long productId)
    {
        ProductResponse product=pService.getProductById(productId);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @PutMapping("/reduceQuantity/{id}")
    public ResponseEntity<Void> reduceQuantity(@PathVariable("id") long productId,@RequestParam long quantity)
    {
        pService.reduceQuantity(productId,quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

