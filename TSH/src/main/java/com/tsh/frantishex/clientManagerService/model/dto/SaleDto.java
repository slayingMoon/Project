package com.tsh.frantishex.clientManagerService.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class SaleDto {

    @Min(0)
    private BigDecimal totalPrice;

    @NotNull
    private List<SaleSaleItemDto> saleItems;

    @NotNull
    private SaleCardDto card;

    @NotNull
    private SaleClientDto client;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public SaleDto setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<SaleSaleItemDto> getSaleItems() {
        return saleItems;
    }

    public SaleDto setSaleItems(List<SaleSaleItemDto> saleItems) {
        this.saleItems = saleItems;
        return this;
    }

    public SaleCardDto getCard() {
        return card;
    }

    public SaleDto setCard(SaleCardDto card) {
        this.card = card;
        return this;
    }

    public SaleClientDto getClient() {
        return client;
    }

    public SaleDto setClient(SaleClientDto client) {
        this.client = client;
        return this;
    }
}
