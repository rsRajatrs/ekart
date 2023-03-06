package com.ekartv2.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekartv2.payload.ProductDto;
import com.ekartv2.payload.SellerDetailDto;
import com.ekartv2.service.SellerService;

@RestController
@RequestMapping("/products")
public class SellerController {
	
	@Autowired
	private SellerService sellerService;
	
	@PostMapping
	public ResponseEntity<Integer> addNewProduct(@RequestBody ProductDto productDto)
	{
		return new ResponseEntity<>(sellerService.addNewProduct(productDto), HttpStatus.CREATED);
	}
	
	@GetMapping("/{sellerId}")
	public ResponseEntity<List<ProductDto>> getSellersProducts(@PathVariable Integer sellerId)
	{
		return ResponseEntity.ok(sellerService.getSellersProducts(sellerId));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		return ResponseEntity.ok(sellerService.getAllProducts());
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<List<ProductDto>> getAllProductsByCategory(@PathVariable String category)
	{
		return ResponseEntity.ok(sellerService.getProductsByCategory(category));
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<ProductDto> getByProductId(@PathVariable Integer productId)
	{
		return ResponseEntity.ok(sellerService.getByProductId(productId));
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<Set<ProductDto>> searchProducts(@PathVariable String keyword)
	{
		return ResponseEntity.ok(sellerService.searchProduct(keyword));
	}
	
	@GetMapping("/sellers/{productId}")
	public ResponseEntity<List<SellerDetailDto>> getAllSellersOfProduct(@PathVariable Integer productId)
	{
		return ResponseEntity.ok(sellerService.getAllSellersOfProduct(productId));
	}
	
	@PutMapping
	public ResponseEntity<Boolean> updateProductDetails(@RequestBody ProductDto productDto)
	{
		return ResponseEntity.ok(sellerService.updateProductDetails(productDto));
	}
	
	@GetMapping("/random/{category}")
	public ResponseEntity<ProductDto> getRandomProductFromCategory(@PathVariable String category)
	{
		return ResponseEntity.ok(sellerService.getRandomProductByCategoryName(category));
	}
	
}
