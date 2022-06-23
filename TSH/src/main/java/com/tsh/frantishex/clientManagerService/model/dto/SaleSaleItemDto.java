package com.tsh.frantishex.clientManagerService.model.dto;


import com.tsh.frantishex.clientManagerService.model.enums.SaleType;

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
