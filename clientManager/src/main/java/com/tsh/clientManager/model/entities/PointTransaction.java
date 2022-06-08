package com.tsh.clientManager.model.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PointTransaction extends BaseEntity {
	@Column(name = "created_on")
	private LocalDate createdOn;
	@Column
	private Integer awarded;
	@Column
	private Integer used;
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
		return used;
	}
	public Integer getDebit() {
		return awarded;
	}
	public PointTransaction setCard(Card card) {
		this.card = card;
		return this;
	}
	public PointTransaction setCreatedOn(LocalDate createdOn) {
		this.createdOn = createdOn;
		return this;
	}
	public PointTransaction setCredit(Integer used) {
		this.used = used;
		return this;
	}
	public PointTransaction setDebit(Integer awarded) {
		this.awarded = awarded;
		return this;
	}

}
