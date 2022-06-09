package com.tsh.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.entities.Client;
import com.tsh.clientManager.model.enums.ClientStatus;
import com.tsh.clientManager.services.ClientService;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ClientServiceTests {
	private Client testCient;
	private ClientPhoneNumberDto testPhone;
	@Autowired
	private ClientService clientService;

	@BeforeEach
	void init() {
		testPhone = new ClientPhoneNumberDto();
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
		testPhone.setPhoneNumber("+7-312-413-7036");
		clientService.clientDeactivation(testPhone);
		testCient=clientService.findClientByPhoneNumber("+7-312-413-7036");
		assertEquals(ClientStatus.DEACTIVATED, testCient.getStatus());
		clientService.clientActivation(testPhone);
		testCient=clientService.findClientByPhoneNumber("+7-312-413-7036");
		assertEquals(ClientStatus.ACTIVATED, testCient.getStatus());
	}
}
