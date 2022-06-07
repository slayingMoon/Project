package com.tsh.clientManager.services;

import com.tsh.clientManager.model.entities.Sale;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class ClientManagerSaleServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CardService cardService;

    private Sale sale;

    @Test
    public void createSalePersistsSaleInDB() {
        Sale sale = new Sale().setTotalPrice(BigDecimal.valueOf(45)).setCard()
    }

}
