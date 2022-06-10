package com.tsh.clientManager;

import com.tsh.clientManager.model.dto.SaleCardDto;
import com.tsh.clientManager.model.dto.SaleClientDto;
import com.tsh.clientManager.model.dto.SaleDto;
import com.tsh.clientManager.model.dto.SaleSaleItemDto;
import com.tsh.clientManager.model.enums.SaleType;
import com.tsh.clientManager.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SaleDemo implements CommandLineRunner {

    @Autowired
    private SaleService saleService;

    @Override
    public void run(String... args) throws Exception {

        SaleClientDto clientDto = new SaleClientDto().setPhoneNumber("+359888888888");
        SaleCardDto saleCardDto = new SaleCardDto().setId(1L);
        SaleSaleItemDto firstItem = new SaleSaleItemDto().setSaleType(SaleType.RESERVATION);
        SaleSaleItemDto secondItem = new SaleSaleItemDto().setSaleType(SaleType.RESERVATION);
        SaleSaleItemDto thirdItem = new SaleSaleItemDto().setSaleType(SaleType.DELIVERY);
        List<SaleSaleItemDto> saleItems = new ArrayList<>(Arrays.asList(firstItem, secondItem, thirdItem));

        SaleDto saleDto = new SaleDto()
                .setTotalPrice(BigDecimal.valueOf(45))
                .setClient(clientDto)
                .setCard(saleCardDto)
                .setSaleItems(saleItems);

        saleService.createSale(saleDto);
    }
}
