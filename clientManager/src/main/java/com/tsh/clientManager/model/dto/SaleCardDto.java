package com.tsh.clientManager.model.dto;

import javax.validation.constraints.NotNull;

public class SaleCardDto {

    @NotNull
    private Long id;

    public Long getId() {

        return id;
    }

    public SaleCardDto setId(Long id) {
        this.id = id;
        return this;
    }
}
