package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.SaleCardDto;
import com.tsh.clientManager.model.dto.SaleClientDto;
import com.tsh.clientManager.model.dto.SaleDto;
import com.tsh.clientManager.model.dto.SaleSaleItemDto;
import com.tsh.clientManager.model.entities.Sale;
import com.tsh.clientManager.model.entities.SaleItem;
import com.tsh.clientManager.model.enums.SaleType;
import com.tsh.clientManager.repository.SaleRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ClientManagerSaleServiceTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private SaleService saleService;

    private SaleDto saleDto;

    @Before
    public void setUp() {
        SaleClientDto clientDto = new SaleClientDto().setPhoneNumber("+359888777155");
        SaleCardDto saleCardDto = new SaleCardDto().setId(1L);
        SaleSaleItemDto firstItem = new SaleSaleItemDto().setSaleType(SaleType.RESERVATION);
        SaleSaleItemDto secondItem = new SaleSaleItemDto().setSaleType(SaleType.RESERVATION);
        SaleSaleItemDto thirdItem = new SaleSaleItemDto().setSaleType(SaleType.DELIVERY);
        List<SaleSaleItemDto> saleItems = new ArrayList<>(Arrays.asList(firstItem, secondItem, thirdItem));

        this.saleDto = new SaleDto()
                .setTotalPrice(BigDecimal.valueOf(45))
                .setClient(clientDto)
                .setCard(saleCardDto)
                .setSaleItems(saleItems);

    }

    @Test
    public void createSalePersistsSaleInDB() {

        Sale sale = saleService.createSale(saleDto);
        Sale saleFound = saleService.findSaleByClient(sale.getClient().getPhoneNumber());

        Assert.assertEquals(sale.getClient().getId(), saleFound.getClient().getId());
    }

}
