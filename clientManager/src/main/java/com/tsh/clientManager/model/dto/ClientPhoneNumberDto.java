package com.tsh.clientManager.model.dto;

import javax.validation.constraints.NotBlank;

public class ClientPhoneNumberDto {

    @NotBlank
    private String phoneNumber;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ClientPhoneNumberDto setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
}
