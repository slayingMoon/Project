package com.tsh.clientManager.services.impl;

import org.springframework.stereotype.Service;

import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.dto.RegisterClientDto;
import com.tsh.clientManager.model.entities.Client;
import com.tsh.clientManager.model.enums.ClientStatus;
import com.tsh.clientManager.repository.ClientRepository;
import com.tsh.clientManager.services.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public Client clientRegistration(RegisterClientDto registerClientDto) {
		Client client = new Client()
				.setFirstName(registerClientDto.getFirstName())
				.setLastName(registerClientDto.getLastName())
				.setAge(registerClientDto.getAge())
				.setPhoneNumber(registerClientDto.getPhoneNumber())
				.setEmail(registerClientDto.getEmail())
				.setStatus(ClientStatus.ACTIVATED);

		clientRepository.save(client);
		return client;
	}

	private Client changeClientStatus(ClientPhoneNumberDto clientNumber,
			ClientStatus status) {
		Client client = findClientByPhoneNumber(clientNumber.getPhoneNumber());
		if (client == null) {
			throw new RuntimeException(
					"Nonexisting client given for status update.");
		}
		client.setStatus(status);
		clientRepository.save(client);
		return client;
	}

	@Override
	public void clientDeactivation(ClientPhoneNumberDto client) {
		changeClientStatus(client, ClientStatus.DEACTIVATED);
	}

	@Override
	public void clientActivation(ClientPhoneNumberDto client) {
		changeClientStatus(client, ClientStatus.ACTIVATED);
	}

	@Override
	public Client findClientByPhoneNumber(String phoneNumber) {
		return clientRepository.findFirstByPhoneNumber(phoneNumber)
				.orElse(null);
	}

	@Override
	public boolean isClientExisting(String phoneNumber) {
		return clientRepository.existsByPhoneNumber(phoneNumber);
	}
}
