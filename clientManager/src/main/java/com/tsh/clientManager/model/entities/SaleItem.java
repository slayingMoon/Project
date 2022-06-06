package com.tsh.clientManager.model.entities;

import com.tsh.clientManager.model.enums.SaleType;

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

    public void setSaleType(SaleType saleType) {
        this.saleType = saleType;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
