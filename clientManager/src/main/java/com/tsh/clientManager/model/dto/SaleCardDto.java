package com.tsh.clientManager.model.dto;

import javax.validation.constraints.NotNull;

public class SaleCardDto {

    @NotNull
    private Long id;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
