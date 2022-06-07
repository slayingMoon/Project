package com.tsh.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.model.entities.Client;
import com.tsh.clientManager.repository.CardRepository;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.services.impl.CardServiceImpl;
import com.tsh.clientManager.util.ValidationUtil;
import com.tsh.clientManager.util.impl.ValidationUtilImpl;

@SpringBootTest
public class ClientManagerCardServiceTests {
	private CardRepository cardRepository = mock(CardRepository.class);
	private ClientService clientService = mock(ClientService.class);
	private ValidationUtil validationUtil = new ValidationUtilImpl();
	private ClientPhoneNumberDto clientPhoneNumberDto;
	private CardService cardService;
	private CardDto cardDto;
	private final Long TEST_CARD_ID = (long) 9999;
	private final Integer TEST_CARD_POINT_TO_USE = 10;

	@BeforeEach
	void init() {
		clientPhoneNumberDto = new ClientPhoneNumberDto()
				.setPhoneNumber("+359886111222");
		cardDto = new CardDto().setBalance(0).setClient(clientPhoneNumberDto);
		cardService = new CardServiceImpl(cardRepository, clientService,
				validationUtil);
	}

	@Test
	public void cardIsInvalidWithNonExistingClient() {
		assertFalse(cardService.isCardValid(cardDto));
	}

	@Test
	public void cardWithNegativeBalanceIsInvalid() {
		when(clientService.isClientExisting("+359895878111")).thenReturn(true);
		cardDto.setBalance(-20).setClient(
				clientPhoneNumberDto.setPhoneNumber("+359895878111"));
		assertFalse(cardService.isCardValid(cardDto));
	}

	@Test
	public void cardWithExistingClientAndAdequateBalance() {
		when(clientService.isClientExisting("+359895878111")).thenReturn(true);
		cardDto.setBalance(0).setClient(
				new ClientPhoneNumberDto().setPhoneNumber("+359895878111"));
		assertTrue(cardService.isCardValid(cardDto));
	}

	@Test
	public void isCreatedCardObjectEquivalentToGivenDto() {
		when(clientService.findClientByPhoneNumber("+359888777999"))
				.thenReturn(new Client().setFirstName("John").setLastName("Doe")
						.setAge(69).setPhoneNumber("+359888777999")
						.setEmail("john.doe@gmail.com"));
		when(cardRepository.save(any(Card.class)))
				.then(answer -> answer.getArguments()[0]);
		when(clientService.isClientExisting("+359888777999")).thenReturn(true);

		cardDto.setBalance(0).setClient(
				new ClientPhoneNumberDto().setPhoneNumber("+359888777999"));
		Card o = cardService.createCard(cardDto);
		assertEquals(o.getClient().getPhoneNumber(),
				cardDto.getClient().getPhoneNumber());
	}

	@Test
	public void usePointsWithNonexistingCardThrowsException() {
		when(cardRepository.findById(any(Long.class))).thenReturn(null);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			cardService.usePoints(TEST_CARD_ID, TEST_CARD_POINT_TO_USE);
		});
		String expectedMessage = "Error: Nonexisting card used for points update.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void receivePointsWithNonexistingCardThrowsException() {
		when(cardRepository.findById(any(Long.class))).thenReturn(null);
		Exception exception = assertThrows(RuntimeException.class, () -> {
			cardService.receivePoints(TEST_CARD_ID, TEST_CARD_POINT_TO_USE);
		});
		String expectedMessage = "Error: Nonexisting card used for points update.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void useMorePointsTHanExistingInCard() {
		when(cardRepository.findById(any(Long.class))).thenReturn(Optional.of(new Card().setBalance(0)));
		Exception exception = assertThrows(RuntimeException.class, () -> {
			cardService.usePoints(TEST_CARD_ID, TEST_CARD_POINT_TO_USE);
		});
		String expectedMessage = "Error: Attempted use of more points than existing.";
		String actualMessage = exception.getMessage();
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void use10PointsWith20Existing() {
		when(cardRepository.findById(any(Long.class))).thenReturn(Optional.of(new Card().setBalance(20)));
		assertEquals(10, cardService.usePoints(TEST_CARD_ID, TEST_CARD_POINT_TO_USE));
	}
	
	@Test
	public void receive10PointsWith20Existing() {
		when(cardRepository.findById(any(Long.class))).thenReturn(Optional.of(new Card().setBalance(20)));
		assertEquals(30, cardService.receivePoints(TEST_CARD_ID, TEST_CARD_POINT_TO_USE));
	}
}
