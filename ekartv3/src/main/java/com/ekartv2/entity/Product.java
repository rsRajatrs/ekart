package com.ekartv2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@NoArgsConstructor
@Getter
@Setter
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Integer productId;
	
	@Column(name = "seller_id")
	private Integer sellerId;
	
	@Column(name = "product_name")
	private String productName;
	
	@Column(name = "product_desc")
	private String productDesc;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "discount %")
	private Integer discount;
	
	@Column(name = "category")
	private String category;
	
}
