package com.tsh.frantishex.clientManagerService.model.dto;

import com.sun.istack.NotNull;
import com.tsh.frantishex.clientManagerService.model.enums.CardTiers;

import java.util.List;



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
