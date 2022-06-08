package com.tsh.clientManager.model.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.tsh.clientManager.model.enums.CardTiers;

@Entity
public class Card extends BaseEntity{

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CardTiers tier;
    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Sale> sales;
    @ManyToOne
    private Client client;

    public CardTiers getTier() {
        return tier;
    }

    public Card setTier(CardTiers tier) {
        this.tier = tier;
        return this;
    }

    public List<Sale> getSales() {
        return sales;
    }

    public Card setSales(List<Sale> sales) {
        this.sales = sales;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Card setClient(Client client) {
        this.client = client;
        return this;
    }
}
