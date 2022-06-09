package com.tsh.clientManager.services;

import org.springframework.stereotype.Service;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.model.enums.CardTiers;
import com.tsh.clientManager.repository.CardRepository;
import com.tsh.clientManager.util.ValidationUtil;

@Service
public class CardService {
	private final CardRepository cardRepository;
	private final ClientService clientService;
	private final ValidationUtil validationUtil;

	public CardService(CardRepository cardRepository,
			ClientService clientService, ValidationUtil validationUtil) {
		this.cardRepository = cardRepository;
		this.clientService = clientService;
		this.validationUtil = validationUtil;
	}

	public Card createCard(CardDto cardDto) {
		if (isCardValid(cardDto)) {
			Card card = new Card().setTier(CardTiers.BRONZE)
					.setClient(clientService.findClientByPhoneNumber(
							cardDto.getClient().getPhoneNumber()));

			cardRepository.save(card);
			return card;
		}
		throw new RuntimeException("Error creating card.");
	}

	public boolean isCardValid(CardDto cardDto) {
		return validationUtil.isValid(cardDto) && clientService
				.isClientExisting(cardDto.getClient().getPhoneNumber());
	}

	public Card findCardById(Long cardId) {
		return cardRepository.findById(cardId).orElse(null);
	}

	public Card findCardByClientPhoneNumber(String phoneNumber) {
		return cardRepository.findCardByClientPhoneNumber(phoneNumber).orElse(null);
	}
}
