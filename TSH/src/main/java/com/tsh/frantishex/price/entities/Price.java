package com.tsh.frantishex.price.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Price extends BaseEntity{
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private PriceCity startPriceCity;
    @OneToOne(fetch = FetchType.EAGER)
    private PriceCity endPriceCity;
    private boolean isOnline;
    private boolean isDoubleWay;

    public PriceCity getStartCity() {
        return startPriceCity;
    }

    public void setStartCity(PriceCity startPriceCity) {
        this.startPriceCity = startPriceCity;
    }

    public PriceCity getEndCity() {
        return endPriceCity;
    }

    public void setEndCity(PriceCity endPriceCity) {
        this.endPriceCity = endPriceCity;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isDoubleWay() {
        return isDoubleWay;
    }

    public void setDoubleWay(boolean doubleWay) {
        isDoubleWay = doubleWay;
    }

    public Price() {
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Price: " +
                "amount=" + amount +
                ", startPriceCity=" + startPriceCity +
                ", endPriceCity=" + endPriceCity +
                ", isOnline=" + isOnline +
                ", isDoubleWay=" + isDoubleWay +
                '}';
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
