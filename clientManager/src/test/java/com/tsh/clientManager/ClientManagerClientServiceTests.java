package com.tsh.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.entities.Client;
import com.tsh.clientManager.model.enums.ClientStatus;
import com.tsh.clientManager.repository.ClientRepository;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.services.impl.ClientServiceImpl;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClientManagerClientServiceTests {
	private Client testCient;
	private ClientPhoneNumberDto testPhone;
	private ClientRepository clientRepository = mock(ClientRepository.class);
	private EntityManager entityManager = mock(EntityManager.class);
	private ClientService clientService = new ClientServiceImpl(
			clientRepository);

	@BeforeEach
	void init() {
		testCient = new Client().setFirstName("Pesho").setLastName("Petkov")
				.setAge(20).setPhoneNumber("+359895878111")
				.setEmail("pesho.petkov@gmail.com")
				.setStatus(ClientStatus.ACTIVATED);
		when(entityManager.contains(any(Client.class))).thenReturn(false);
		testPhone = new ClientPhoneNumberDto().setPhoneNumber("+359895878111");
	}

	@Test
	void updateOnNonExistingThrowsException() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			clientService.clientDeactivation(new ClientPhoneNumberDto());
		});
		String expectedMessage = "Nonexisting client given for status update.";
		String actualMessage = exception.getMessage();

		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	void handleUpdateChangesStatus() {
		when(entityManager.contains(any(Client.class))).thenReturn(false);
		when(clientRepository.save(any(Client.class)))
				.then(answer -> answer.getArguments()[0]);
		when(clientRepository
				.findFirstByPhoneNumber(testPhone.getPhoneNumber()))
				.thenReturn(Optional.ofNullable(testCient));
		clientService.clientDeactivation(testPhone);
		assertEquals(ClientStatus.DEACTIVATED, testCient.getStatus());
	}
}
