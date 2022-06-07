package com.tsh.clientManager.services.impl;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.tsh.clientManager.model.dto.TransactionDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.model.entities.PointTransaction;
import com.tsh.clientManager.repository.PointTransactionRepository;
import com.tsh.clientManager.services.PointTransactionService;

@Service
public class PointTransactionServiceImpl implements PointTransactionService {

	@Autowired
	private PointTransactionRepository pointTransactionRepository;

	@Override
	public void createTransaction(TransactionDto transaction) {
		PointTransaction pointTransaction = new PointTransaction()
				.setCard(transaction.getCard()).setCreatedOn(LocalDate.now())
				.setCredit(0).setDebit(transaction.getDebit());
		pointTransactionRepository.save(pointTransaction);
	}

	@Override
	public Integer getPoints(Long cardId) {
		return getTransactionHistoryForLast365Days(cardId).parallelStream().reduce(0,
				(sum, elem) -> sum + elem.getDebit() - elem.getCredit(),
				Integer::sum);
	}

	@Override
	public void usePoints(Integer credit, Long cardId) {
		List<PointTransaction> history = getSortedTransactionsWithDebitLeft(cardId);
		Integer pointsToUse = credit;
		for (PointTransaction transaction : history) {
			Integer debitLeft = transaction.getDebit() - transaction.getCredit();
			if (debitLeft < pointsToUse) {
				pointsToUse = pointsToUse - debitLeft;
				transaction.setCredit(transaction.getDebit());
				pointTransactionRepository.save(transaction);
			} else {
				transaction.setCredit(pointsToUse);
				pointsToUse = 0;
				pointTransactionRepository.save(transaction);
				break;
			}
		}
	}

	private List<PointTransaction> getSortedTransactionsWithDebitLeft(Long cardId) {
		return getTransactionHistoryForLast365Days(cardId).stream()
				.filter(elem -> elem.getDebit() > elem.getCredit())
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
