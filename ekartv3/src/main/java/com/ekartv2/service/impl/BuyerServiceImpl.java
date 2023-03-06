package com.ekartv2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekartv2.entity.Product;
import com.ekartv2.payload.DealsDto;
import com.ekartv2.payload.ProductDto;
import com.ekartv2.repository.ProductRepo;
import com.ekartv2.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService {
	
	@Autowired
	private ProductRepo productRepo;
	
	static boolean dealLive = false;

	@Override
	public List<ProductDto> getProductsWithDeals() {

		List<String> category = List.of("mobile", "Television", "Watch");
		
		Random rand = new Random();
		String randomCategory = category.get(rand.nextInt(category.size()));
		
		DealsDto deal1 = new DealsDto();
		deal1.setDealId(1);
		deal1.setDealDesc("25% Off on products with Price>=10000 in " + randomCategory);
		
		DealsDto deal2 = new DealsDto();
		deal2.setDealId(2);
		deal2.setDealDesc("Flat 1500 off on products with Price >=5000 in " + randomCategory);
		
		
		List<Product> products = productRepo.findByCategory(randomCategory);
		
		List<Integer> dealIds = List.of(1,2);
		
		Integer randomDeal = dealIds.get(rand.nextInt(dealIds.size()));
		
		List<ProductDto> productDtos = new ArrayList<>();
		
		switch(randomDeal)
		{
		
			case 1: products.forEach((prod) -> {
				
				if(prod.getPrice()>=10000)
				{
					ProductDto productDto = SellerServiceImpl.productToDto(prod);
					productDto.setDeal("25% Off on products with Price>=10000 in " + randomCategory);
					productDto.setDealPrice(prod.getPrice() - (prod.getPrice()*25)/100);
					productDtos.add(productDto);
				}
				
			});
			return productDtos;
		
			case 2: products.forEach((prod) -> {
				
				if(prod.getPrice()>=5000)
				{
					ProductDto productDto = SellerServiceImpl.productToDto(prod);
					productDto.setDeal("Flat 1500 off on products with Price >=5000 in " + randomCategory);
					productDto.setDealPrice(prod.getPrice()-1500);
					productDtos.add(productDto);
				}
				
			});
			return productDtos; 
			
			default: return null;
		}
		
		
	}

}
