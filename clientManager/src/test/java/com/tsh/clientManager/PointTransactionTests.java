package com.tsh.clientManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.dto.TransactionDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.model.entities.PointTransaction;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.PointTransactionService;

@SpringBootTest
public class PointTransactionTests {
	@Autowired
	private PointTransactionService pointTransactionService;
	@Autowired
	private CardService cardService;
	private static Card card;
	private PointTransaction testTransaction;

	@BeforeAll
	public static void setup(@Autowired CardService cardService) {
		card = cardService
				.createCard(new CardDto().setClient(new ClientPhoneNumberDto()
						.setPhoneNumber("+381-941-704-7709")));
	}

	@Test
	public void createTransactionCreatingWithCurrentDate() {
		testTransaction = pointTransactionService.createTransaction(
				new TransactionDto().setCard(card).setDebit(10));
		assertEquals(testTransaction.getCreatedOn(), LocalDate.now());
	}

	@Test
	public void createTransactionWithAwarded0() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			pointTransactionService.createTransaction(
					new TransactionDto().setCard(card).setDebit(0));
		});
		assertEquals("Error creating transaction.", exception.getMessage());
	}

	@Test
	public void createTransactionWithAwardedNegative() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			pointTransactionService.createTransaction(
					new TransactionDto().setCard(card).setDebit(-10));
		});
		assertEquals("Error creating transaction.", exception.getMessage());
	}

	@Test
	public void createTransactionWithNonexistingCard() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			pointTransactionService.createTransaction(new TransactionDto()
					.setCard((Card) new Card().setId((long) -1)).setDebit(10));
		});
		assertEquals("Error creating transaction.", exception.getMessage());
	}

	@Test
	public void createTransactionWithNullCard() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			pointTransactionService.createTransaction(
					new TransactionDto().setCard(null).setDebit(-10));
		});
		assertEquals("Error creating transaction.", exception.getMessage());
	}

	@Test
	public void getPointsOfTestPerson() {
		Card card = cardService.findCardByClientPhoneNumber("+359888888888");
		Integer points = pointTransactionService.getPoints(card.getId());
		assertEquals(points, 25);
	}

	@Test
	public void getPointsWithNonexistingCardId() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			pointTransactionService.getPoints((long) -1);
		});
		assertEquals("Error getting points: Non-existing id given.",
				exception.getMessage());
	}
	
	@Test
	public void useMorePointsThanAvailableThrowsException() {
		Exception exception = assertThrows(RuntimeException.class, () -> {
			pointTransactionService.usePoints(100, (long) 11);
		});
		assertEquals("Attempt to use more points than available.",
				exception.getMessage());
	}
	
	@Test
	public void use20PointsFrom25FromTestPerson() {
		pointTransactionService.usePoints(20, (long) 11);
		assertEquals(pointTransactionService.getPoints((long) 11), 5);
	}
}
