package com.tsh.frantishex.clientManagerServiceTest.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import com.tsh.frantishex.clientManagerService.model.entities.Sale;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;


@SpringBootTest
@Sql("/clientManagerProperties/data-client-manager.sql")
@TestPropertySource(locations= "classpath:clientManagerProperties/clientManagerTest.properties")
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
