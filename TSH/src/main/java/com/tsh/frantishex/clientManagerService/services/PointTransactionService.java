package com.tsh.frantishex.clientManagerService.services;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.tsh.frantishex.clientManagerService.model.dto.TransactionDto;
import com.tsh.frantishex.clientManagerService.model.entities.Card;
import com.tsh.frantishex.clientManagerService.model.entities.PointTransaction;
import com.tsh.frantishex.clientManagerService.repository.CardRepository;
import com.tsh.frantishex.clientManagerService.repository.PointTransactionRepository;
import com.tsh.frantishex.clientManagerService.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;


@Service
public class PointTransactionService {

	@Autowired
	private PointTransactionRepository pointTransactionRepository;
	@Autowired
	private CardRepository cardRepository;
	private final ValidationUtil validationUtil = new ValidationUtil();

	public PointTransaction createTransaction(TransactionDto transaction) {
		if (isTransactionValid(transaction)) {
			PointTransaction pointTransaction = new PointTransaction()
					.setCard(transaction.getCard())
					.setCreatedOn(LocalDate.now()).setUsed(0)
					.setAwarded(transaction.getDebit());

			pointTransactionRepository.save(pointTransaction);
			return pointTransaction;
		}
		throw new RuntimeException("Error creating transaction.");
	}

	private boolean isTransactionValid(TransactionDto transaction) {
		return validationUtil.isValid(transaction)
				&& isCardExisting(transaction.getCard().getId());
	}

	private boolean isCardExisting(Long cardId) {
		return cardRepository.existsById(cardId);
	}

	public Integer getPoints(Long cardId) {
		if (isCardExisting(cardId)) {
			return getTransactionHistoryForLast365Days(cardId).parallelStream()
					.reduce(0, (sum, elem) -> sum + elem.getAwarded()
							- elem.getUsed(), Integer::sum);
		}
		throw new RuntimeException(
				"Error getting points: Non-existing id given.");
	}

	public void usePoints(Integer used, Long cardId) {
		validateUsePointsData(used, cardId);
		List<PointTransaction> history = getSortedTransactionsWithDebitLeft(
				cardId);
		Integer pointsToUse = used;
		for (PointTransaction transaction : history) {
			Integer awardedPointsLeft = transaction.getAwarded()
					- transaction.getUsed();
			if (awardedPointsLeft < pointsToUse) {
				pointsToUse -= awardedPointsLeft;
				transaction.setUsed(transaction.getAwarded());
				pointTransactionRepository.save(transaction);
			} else {
				transaction.setUsed(pointsToUse);
				pointsToUse = 0;
				pointTransactionRepository.save(transaction);
				break;
			}
		}
	}

	private void validateUsePointsData(Integer credit, Long cardId) {
		if (!isCardExisting(cardId)) {
			throw new RuntimeException(
					"Error using points: Non-existing id given.");
		}
		if (getPoints(cardId) < credit) {
			throw new RuntimeException(
					"Attempt to use more points than available.");
		}
	}

	private List<PointTransaction> getSortedTransactionsWithDebitLeft(
			Long cardId) {
		return getTransactionHistoryForLast365Days(cardId).stream()
				.filter(elem -> elem.getAwarded() > elem.getUsed())
				.sorted(Comparator.comparing(PointTransaction::getCreatedOn))
				.collect(Collectors.toList());
	}

	private List<PointTransaction> getTransactionHistoryForLast365Days(
			Long cardId) {
		List<PointTransaction> history = pointTransactionRepository
				.findAll(Example.of(new PointTransaction()
						.setCard((Card) new Card().setId(cardId))));
		return history.stream()
				.filter(element -> element.getCreatedOn()
						.isAfter(LocalDate.now().minusDays(365)))
				.collect(Collectors.toList());
	}
}
