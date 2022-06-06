package com.tsh.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tsh.clientManager.model.entities.Sale;

@SpringBootTest
class ClientManagerApplicationTests {

	private Sale sale = new Sale();
	
	@Test
	public void testGetTotalPrice() {
		sale.setTotalPrice(new BigDecimal(150));
		assertEquals(sale.getTotalPrice(), new BigDecimal(150));
	}
	
	@Test
	void contextLoads() {
	}
	
	

}
