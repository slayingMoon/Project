package com.tsh.price.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Price extends BaseEntity{
    private BigDecimal amount;
    @OneToOne(fetch = FetchType.EAGER)
    private City startCity;
    @OneToOne(fetch = FetchType.EAGER)
    private City endCity;
    private boolean isOnline;
    private boolean isDoubleWay;

    public City getStartCity() {
        return startCity;
    }

    public void setStartCity(City startCity) {
        this.startCity = startCity;
    }

    public City getEndCity() {
        return endCity;
    }

    public void setEndCity(City endCity) {
        this.endCity = endCity;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
