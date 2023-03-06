package com.ekartv2;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EkartV2ApplicationTests {

	private Calculator c = new Calculator();
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void testSum()
	{
		//expected
		int expectedResult = 17;
		
		//actual 
		int actualResult = c.doSum(12,3,2);
		
		assertThat(actualResult).isEqualTo(expectedResult);
	}

}
