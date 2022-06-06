package com.tsh.clientManager.model.dto;

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

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<SaleSaleItemDto> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<SaleSaleItemDto> saleItems) {
        this.saleItems = saleItems;
    }

    public SaleCardDto getCard() {
        return card;
    }

    public void setCard(SaleCardDto card) {
        this.card = card;
    }

    public SaleClientDto getClient() {
        return client;
    }

    public void setClient(SaleClientDto client) {
        this.client = client;
    }
}
