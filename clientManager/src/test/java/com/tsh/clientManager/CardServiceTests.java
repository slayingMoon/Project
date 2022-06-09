package com.tsh.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.dto.RegisterClientDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.model.enums.CardTiers;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;

@SpringBootTest
public class CardServiceTests {
	@Autowired
	private CardService cardService;
	@Autowired
	private ClientService clientService;

	private CardDto cardDto;
	private ClientPhoneNumberDto clientPhoneNumberDto;
	private final String existingPhone = "+7-312-413-7036";

	@BeforeEach
	void init() {
		clientPhoneNumberDto = new ClientPhoneNumberDto()
				.setPhoneNumber("+359886111222");
		cardDto = new CardDto().setTier(CardTiers.BRONZE)
				.setClient(clientPhoneNumberDto);
	}

	@Test
	public void cardIsInvalidWithNonExistingClient() {
		assertFalse(cardService.isCardValid(cardDto));
	}

	@Test
	public void isCreatedCardObjectEquivalentToGivenDto() {
		final String nonExistingPhone = "+1234567890";
		clientService.clientRegistration(
				new RegisterClientDto().setFirstName("Jane").setLastName("Doe")
						.setAge(60).setEmail("janeDoe@gmail.com")
						.setPhoneNumber(nonExistingPhone));
		cardDto.setClient(
				new ClientPhoneNumberDto().setPhoneNumber(nonExistingPhone));
		Card o = cardService.createCard(cardDto);
		assertEquals(o.getClient().getPhoneNumber(),
				cardDto.getClient().getPhoneNumber());
	}

	@Test
	public void findCardByExistingClientNumberIsValid() {
		Card card = cardService.findCardByClientPhoneNumber(existingPhone);
		assertEquals(card.getClient().getPhoneNumber(), existingPhone);
	}

	@Test
	public void findCardByNonExistingClientNumberIsNull() {
		Card card = cardService.findCardByClientPhoneNumber("-1");
		assertNull(card);
	}

	@Test
	public void findCardByExistingIdIsValid() {
		Card card = cardService.findCardById((long) 2);
		assertEquals(card.getClient().getPhoneNumber(), existingPhone);
	}

	@Test
	public void findCardByNonExistingIdIsNull() {
		Card card = cardService.findCardById((long) -1);
		assertNull(card);
	}
}
