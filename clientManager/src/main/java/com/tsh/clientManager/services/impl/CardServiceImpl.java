package com.tsh.clientManager.services.impl;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.repository.CardRepository;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.util.ValidationUtil;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

	private final CardRepository cardRepository;
	private final ClientService clientService;
	private final ValidationUtil validationUtil;

	public CardServiceImpl(CardRepository cardRepository,
			ClientService clientService, ValidationUtil validationUtil) {
		this.cardRepository = cardRepository;
		this.clientService = clientService;
		this.validationUtil = validationUtil;
	}

	@Override
	public Integer usePoints(Long cardId, Integer amount) {
		return updatePoints(cardId, Math.negateExact(amount));
	}

	@Override
	public Integer receivePoints(Long cardId, Integer amount) {
		return updatePoints(cardId, amount);
	}

	private Integer updatePoints(Long cardId, Integer amount) {
		Optional<Card> foundCard = cardRepository.findById(cardId);
		if (foundCard == null) {
			throw new RuntimeException(
					"Error: Nonexisting card used for points update.");
		}
		Card card = foundCard.get();
		Integer newBalance = card.getBalance() + amount;
		if (newBalance < 0) {
			throw new RuntimeException(
					"Error: Attempted use of more points than existing.");
		}
		
		card.setBalance(newBalance);
		cardRepository.save(card);
		return newBalance;
	}

	@Override
	public Card createCard(CardDto cardDto) {
		if (isCardValid(cardDto)) {
			Card card = new Card().setBalance(cardDto.getBalance())
					.setClient(clientService.findClientByPhoneNumber(
							cardDto.getClient().getPhoneNumber()));

			cardRepository.save(card);
			return card;
		}
		throw new RuntimeException("Error creating card.");
	}

	@Override
	public boolean isCardValid(CardDto cardDto) {
		return validationUtil.isValid(cardDto) && clientService
				.isClientExisting(cardDto.getClient().getPhoneNumber());
	}
}
