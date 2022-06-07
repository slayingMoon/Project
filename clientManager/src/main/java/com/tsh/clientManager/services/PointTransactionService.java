package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.TransactionDto;

public interface PointTransactionService {
	public void createTransaction(TransactionDto transaction);
	public Integer getPoints(Long cardId);
	public void usePoints(Integer credit, Long cardId);
}
