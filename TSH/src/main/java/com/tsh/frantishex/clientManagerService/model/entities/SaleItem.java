package com.tsh.frantishex.clientManagerService.model.entities;


import com.tsh.frantishex.clientManagerService.model.enums.SaleType;

import javax.persistence.*;

@Entity
public class SaleItem extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    @Column(name = "sale_type")
    private SaleType saleType;
    @ManyToOne
    @JoinColumn(name = "sale_id")
    private Sale sale;

    public SaleType getSaleType() {
        return saleType;
    }

    public SaleItem setSaleType(SaleType saleType) {
        this.saleType = saleType;
        return this;
    }

    public Sale getSale() {
        return sale;
    }

    public SaleItem setSale(Sale sale) {
        this.sale = sale;
        return this;
    }
}
