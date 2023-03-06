package com.ekartv2.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekartv2.payload.ProductDto;
import com.ekartv2.service.BuyerService;

@RestController
@RequestMapping("/buyers")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	
	@GetMapping("/deals")
	public ResponseEntity<List<ProductDto>> getProductsWithDeals()
	{
		return ResponseEntity.ok(buyerService.getProductsWithDeals());
	}
	
}
