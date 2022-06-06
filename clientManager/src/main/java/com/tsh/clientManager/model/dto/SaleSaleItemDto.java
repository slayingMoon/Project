package com.tsh.clientManager.model.dto;

import com.tsh.clientManager.model.enums.SaleType;

import javax.validation.constraints.NotNull;

public class SaleSaleItemDto {

    @NotNull
    private SaleType saleType;

    public SaleType getSaleType() {
        return saleType;
    }

    public SaleSaleItemDto setSaleType(SaleType saleType) {
        this.saleType = saleType;
        return this;
    }
}
