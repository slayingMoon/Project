package com.tsh.clientManager.model.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class PointTransaction extends BaseEntity {
	@Column(name = "created_on")
	private LocalDate createdOn;
	@Column
	private Integer debit;
	@Column
	private Integer credit;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "card_id")
	private Card card;
	public Card getCard() {
		return card;
	}
	public LocalDate getCreatedOn() {
		return createdOn;
	}
	public Integer getCredit() {
		return credit;
	}
	public Integer getDebit() {
		return debit;
	}
	public PointTransaction setCard(Card card) {
		this.card = card;
		return this;
	}
	public PointTransaction setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	public PointTransaction setCredit(Integer credit) {
		this.credit = credit;
		return this;
	}
	public PointTransaction setDebit(Integer debit) {
		this.debit = debit;
		return this;
	}

}
