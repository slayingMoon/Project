package com.tsh.frantishex.clientManagerService.model.dto;



import com.tsh.frantishex.clientManagerService.model.enums.CardTiers;

import javax.validation.constraints.NotNull;

public class SaleCardDto {

    @NotNull
    private Long id;

    @NotNull
    private CardTiers tier;

    public Long getId() {

        return id;
    }

    public SaleCardDto setId(Long id) {
        this.id = id;
        return this;
    }

    public CardTiers getTier() {
        return tier;
    }

    public SaleCardDto setTier(CardTiers tier) {
        this.tier = tier;
        return this;
    }
}
