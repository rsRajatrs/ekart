package com.ekartv2.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekartv2.entity.Account;
import com.ekartv2.entity.Product;
import com.ekartv2.exceptions.ErrorMessageException;
import com.ekartv2.payload.ProductDto;
import com.ekartv2.payload.SellerDetailDto;
import com.ekartv2.repository.AccountRepo;
import com.ekartv2.repository.ProductRepo;
import com.ekartv2.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private AccountRepo accountRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public Integer addNewProduct(ProductDto productDto) {
		
		if(accountRepo.findByUserEmail(AccountServiceImpl.userSession)==null)
		{
			throw new ErrorMessageException("Please login to add products!!!");
		}
		
		List<Product> products = productRepo.findByProductName(productDto.getProductName());
		
		products.forEach((prod) -> {
			
			if (prod.getSellerId()==accountRepo.findByUserEmail(AccountServiceImpl.userSession).getUserId())
			{
				throw new ErrorMessageException("You have already added a product with same name");
			}
			
		});
		
		Product product = dtoToProduct(productDto);
		product.setSellerId(accountRepo.findByUserEmail(AccountServiceImpl.userSession).getUserId());
		
		Product savedProduct = productRepo.save(product);
		return savedProduct.getProductId();
	}

	@Override
	public List<ProductDto> getSellersProducts(Integer sellerId) {
		
		List<ProductDto> productDtos = productRepo.findBySellerId(sellerId).stream().map(SellerServiceImpl::productToDto).collect(Collectors.toList());
		productDtos.forEach((prod) -> System.out.println(prod.getProductName()));
		
		return productDtos;
	}

	@Override
	public Boolean updateProductDetails(ProductDto productDto) {
		
		
		Product product = productRepo.findById(productDto.getProductId()).orElseThrow(() -> new ErrorMessageException("product not found"));
		
		product.setProductName(productDto.getProductName());
		product.setPrice(productDto.getPrice());
		product.setDiscount(productDto.getDiscount());
		
		productRepo.save(product);
		
		return true;
	}

	@Override
	public List<ProductDto> getAllProducts() {
		List<ProductDto> productDtos = productRepo.findAll().stream().map(SellerServiceImpl::productToDto).collect(Collectors.toList());
		return productDtos;
	}

	@Override
	public Set<ProductDto> searchProduct(String keyword) {
		
		Set<ProductDto> products = productRepo.findByProductNameContaining(keyword).stream().map(SellerServiceImpl::productToDto).collect(Collectors.toSet())	;	
		return products;
	}

	@Override
	public List<ProductDto> getProductsByCategory(String categoryName) {
		
		List<ProductDto> products= productRepo.findByCategory(categoryName).stream().map(SellerServiceImpl::productToDto).collect(Collectors.toList());
		
		return products;
	}

	@Override
	public ProductDto getByProductId(Integer productId) {
		
		Product product= productRepo.findById(productId).orElseThrow(() -> new ErrorMessageException("Product not found"));
		
		return productToDto(product);
	}

	@Override
	public List<SellerDetailDto> getAllSellersOfProduct(Integer productId) {
		
		Product product = productRepo.findById(productId).orElseThrow(() -> new ErrorMessageException("Product Not Found"));
		String productName = product.getProductName();
		
		List<Product> products = productRepo.findByProductName(productName);
		
		List<Integer> sellerIds = new ArrayList<>();
		products.forEach((prod) -> sellerIds.add(prod.getSellerId()));
		
		List<Account> accounts = accountRepo.findAllById(sellerIds);
		
		List<SellerDetailDto> sellersList = new ArrayList<>();
		
		accounts.forEach((acc) -> {
			
			SellerDetailDto sellerDetails = new SellerDetailDto();
			sellerDetails.setFirstName(acc.getFirstName());
			sellerDetails.setLastName(acc.getLastName());
			sellersList.add(sellerDetails);
		});
		
		return sellersList;
	}

	@Override
	public ProductDto getRandomProductByCategoryName(String categoryName) {
		
		List<Product> products = productRepo.findByCategory(categoryName);
		
		Random rand = new Random();
		Product randomProduct = products.get(rand.nextInt(products.size()));
		
		return productToDto(randomProduct);
	}
	
	private static Product dtoToProduct(ProductDto productDto)
	{
		Product product = new Product();
		product.setProductName(productDto.getProductName());
		product.setProductDesc(productDto.getProductDesc());
		product.setPrice(productDto.getPrice());
		product.setDiscount(productDto.getDiscount());
		product.setCategory(productDto.getCategory());
		return product;
	}

	 static ProductDto productToDto(Product product)
	{
		ProductDto productDto = new ProductDto();
		productDto.setProductId(product.getProductId());
		productDto.setProductName(product.getProductName());
		productDto.setProductDesc(product.getProductDesc());
		productDto.setPrice(product.getPrice());
		productDto.setDiscount(product.getDiscount());
		productDto.setCategory(product.getCategory());
		
		Integer discount = Integer.valueOf(product.getDiscount());
		productDto.setPriceAfterDiscount(product.getPrice() - (product.getPrice()*discount)/100);
		productDto.setDealPrice(product.getPrice() - (product.getPrice()*discount)/100);
		
		return productDto;
	}
}
