package com.tsh.frantishex.clientManagerService.model.dto;

import com.tsh.frantishex.clientManagerService.model.entities.Card;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
