package com.ekartv2.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class ProductDto {

	private Integer productId;
	
	private String productName;
	
	private String productDesc;
	
	private Integer price;
	
	private Integer discount;
	
	private String category;
	
	private Integer priceAfterDiscount;
	
	private String deal;
	
	private Integer dealPrice;
	
}
