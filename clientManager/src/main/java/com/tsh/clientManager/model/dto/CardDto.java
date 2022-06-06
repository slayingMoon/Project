package com.tsh.clientManager.model.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CardDto {

    @Min(0)
    private Integer balance;
    private List<SaleDto> sales;
    @NotNull
    private ClientPhoneNumberDto client;

    public Integer getBalance() {
        return balance;
    }

    public CardDto setBalance(Integer balance) {
        this.balance = balance;
        return this;
    }

    public List<SaleDto> getSales() {
        return sales;
    }

    public CardDto setSales(List<SaleDto> sales) {
        this.sales = sales;
        return this;
    }

    public ClientPhoneNumberDto getClient() {
        return client;
    }

    public CardDto setClient(ClientPhoneNumberDto client) {
        this.client = client;
        return this;
    }
}
