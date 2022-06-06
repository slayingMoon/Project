package com.tsh.clientManager.model.dto;

import javax.validation.constraints.NotNull;

public class SaleClientDto {

    @NotNull
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
