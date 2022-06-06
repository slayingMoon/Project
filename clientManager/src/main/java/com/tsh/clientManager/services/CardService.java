package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.dto.SaleCardDto;
import com.tsh.clientManager.model.entities.Card;

import java.util.Optional;

public interface CardService {

    Integer usePoints();
    Integer receivePoints();
    Card createCard(CardDto cardDto);
	boolean isCardValid(CardDto cardDto);

    Optional<Card> findCardById(SaleCardDto card);
}
