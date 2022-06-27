package com.tsh.frantishex.clientManagerServiceTest.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;

import com.tsh.frantishex.clientManagerService.model.dto.CardDto;
import com.tsh.frantishex.clientManagerService.model.dto.ClientPhoneNumberDto;
import com.tsh.frantishex.clientManagerService.model.dto.RegisterClientDto;
import com.tsh.frantishex.clientManagerService.model.entities.Card;
import com.tsh.frantishex.clientManagerService.model.enums.CardTiers;
import com.tsh.frantishex.clientManagerService.services.CardService;
import com.tsh.frantishex.clientManagerService.services.ClientService;


@SpringBootTest
@SqlGroup({
	@Sql("/clientManagerProperties/data-client-manager.sql"),
	@Sql(scripts="/clientManagerProperties/truncate_tables.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
})
@TestPropertySource(locations= "classpath:clientManagerProperties/clientManagerTest.properties")
public class CardServiceTests {
	@Autowired
	private CardService cardService;
	@Autowired
	private ClientService clientService;

	private ClientPhoneNumberDto clientPhoneNumberDto = new ClientPhoneNumberDto()
	.setPhoneNumber("+359886111222");
	private CardDto cardDto=new CardDto().setTier(CardTiers.BRONZE)
			.setClient(clientPhoneNumberDto);
	private final String existingPhone = "+7-312-413-7036";

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
