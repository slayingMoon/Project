package com.tsh.clientManager.model.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.tsh.clientManager.model.entities.Card;

public class TransactionDto {
	@Positive
	private Integer debit;
	@NotNull
	private Card card;
	public Integer getDebit() {
		return debit;
	}
	public TransactionDto setDebit(Integer debit) {
		this.debit = debit;
		return this;
	}
	public Card getCard() {
		return card;
	}
	public TransactionDto setCard(Card card) {
		this.card = card;
		return this;
	}
}
