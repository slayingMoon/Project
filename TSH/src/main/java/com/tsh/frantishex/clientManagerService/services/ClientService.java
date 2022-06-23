package com.tsh.frantishex.clientManagerService.services;

import com.tsh.frantishex.clientManagerService.model.dto.ClientPhoneNumberDto;
import com.tsh.frantishex.clientManagerService.model.dto.RegisterClientDto;
import com.tsh.frantishex.clientManagerService.model.entities.Client;
import com.tsh.frantishex.clientManagerService.model.enums.ClientStatus;
import com.tsh.frantishex.clientManagerService.repository.ClientRepository;
import org.springframework.stereotype.Service;



@Service
public class ClientService {

	private final ClientRepository clientRepository;

	public ClientService(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

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

	public void clientDeactivation(ClientPhoneNumberDto client) {
		changeClientStatus(client, ClientStatus.DEACTIVATED);
	}

	public void clientActivation(ClientPhoneNumberDto client) {
		changeClientStatus(client, ClientStatus.ACTIVATED);
	}

	public Client findClientByPhoneNumber(String phoneNumber) {
		return clientRepository.findFirstByPhoneNumber(phoneNumber)
				.orElse(null);
	}

	public boolean isClientExisting(String phoneNumber) {
		return clientRepository.existsByPhoneNumber(phoneNumber);
	}
}
