package com.tsh.clientManager.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;

@Entity
public class Card extends BaseEntity{

    @Column(nullable = false)
    @Min(0)
    private Integer balance;
    @OneToMany(mappedBy = "card", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Sale> sales;
    @ManyToOne
    private Client client;

    public Integer getBalance() {
        return balance;
    }

    public Card setBalance(Integer balance) {
        this.balance = balance;
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
