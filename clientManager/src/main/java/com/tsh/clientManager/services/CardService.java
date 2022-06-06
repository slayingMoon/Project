package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.entities.Card;

public interface CardService {

    Integer usePoints();
    Integer receivePoints();
    Card createCard(CardDto cardDto);
	boolean isCardValid(CardDto cardDto);
}
