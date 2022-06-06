package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.dto.RegisterClientDto;
import com.tsh.clientManager.model.entities.Client;

public interface ClientService {

    Client clientRegistration(RegisterClientDto registerClientDto);
    Client findClientByPhoneNumber(String phoneNumber);
    boolean isClientExisting(String phoneNumber);
    void clientActivation(ClientPhoneNumberDto client);
	void clientDeactivation(ClientPhoneNumberDto client);
}
