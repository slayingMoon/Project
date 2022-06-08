package com.tsh.clientManager.services.impl;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.dto.SaleCardDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.repository.CardRepository;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
	public void usePoints(Long cardId, Integer amount) {
		Card card = cardRepository.findById(cardId).get();
		card.setBalance(card.getBalance() - amount);
	}

	@Override
	public void receivePoints(Long cardId, Integer amount) {
		Card card = cardRepository.findById(cardId).get();
		card.setBalance(card.getBalance() + amount);
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

	@Override
	public Card findCardById(Long id) {
		return cardRepository.findById(id)
				.orElse(null);
	}
}
