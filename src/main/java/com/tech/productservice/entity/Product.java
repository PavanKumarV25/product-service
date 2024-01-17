package com.tech.productservice.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long productId;
    @Column(name="PRODUCT_NAME")
    private String productName;
    @Column(name="PRICE")
    private Long price;
    @Column(name="QUANTITY")
    private Long quantity;
	public long getProductId() {
		
		return productId;
	}


}
