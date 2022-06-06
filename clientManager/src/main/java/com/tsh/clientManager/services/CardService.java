package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.entities.Card;

public interface CardService {

    Card createCard(CardDto cardDto);
	boolean isCardValid(CardDto cardDto);
	void receivePoints(Long cardId, Integer amount);
	void usePoints(Long cardId, Integer amount);
}
