package com.tsh.clientManager.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Sale extends BaseEntity{

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
    @OneToMany(mappedBy = "sale", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<SaleItem> saleItems;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Sale setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    public List<SaleItem> getSaleItems() {
        return saleItems;
    }

    public Sale setSaleItems(List<SaleItem> saleItems) {
        this.saleItems = saleItems;
        return this;
    }

    public Card getCard() {
        return card;
    }

    public Sale setCard(Card card) {
        this.card = card;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Sale setClient(Client client) {
        this.client = client;
        return this;
    }
}
