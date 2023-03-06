package com.ekartv2.service;

import java.util.List;
import java.util.Set;

import com.ekartv2.payload.ProductDto;
import com.ekartv2.payload.SellerDetailDto;

public interface SellerService {

	public Integer addNewProduct(ProductDto productDto);
	
	public List<ProductDto> getSellersProducts(Integer sellerId);
	
	public Boolean updateProductDetails(ProductDto productDto);
	
	public List<ProductDto> getAllProducts();
	
	public Set<ProductDto> searchProduct(String keyword);
	
	public List<ProductDto> getProductsByCategory(String categoryName);
	
	public ProductDto getByProductId(Integer productId);
	
	public List<SellerDetailDto> getAllSellersOfProduct(Integer productId);
	
	public ProductDto getRandomProductByCategoryName(String categoryName);
	
}
