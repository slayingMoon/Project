package com.tsh.clientManager.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.tsh.clientManager.model.enums.CardTiers;

public class CardDto {

    private CardTiers tier;
    private List<SaleDto> sales;
    @NotNull
    private ClientPhoneNumberDto client;

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

	public CardTiers getTier() {
		return tier;
	}

	public CardDto setTier(CardTiers tier) {
		this.tier = tier;
		return this;
	}
}
