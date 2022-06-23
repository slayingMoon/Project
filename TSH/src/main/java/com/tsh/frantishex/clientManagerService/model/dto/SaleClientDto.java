package com.tsh.frantishex.clientManagerService.model.dto;

import javax.validation.constraints.NotNull;

public class SaleClientDto {

    @NotNull
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SaleClientDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
