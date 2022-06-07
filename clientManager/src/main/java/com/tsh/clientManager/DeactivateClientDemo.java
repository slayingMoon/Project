package com.tsh.clientManager;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tsh.clientManager.model.dto.TransactionDto;
import com.tsh.clientManager.model.entities.Card;
import com.tsh.clientManager.repository.CardRepository;
import com.tsh.clientManager.repository.PointTransactionRepository;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.services.PointTransactionService;

@Component
public class DeactivateClientDemo implements CommandLineRunner {

	@Autowired
	ClientService clientService;
	@Autowired
	EntityManager entityManager;
	@Autowired
	CardService cardService;
	@Autowired
	PointTransactionRepository pointTransactionRepository;
	@Autowired
	PointTransactionService pointTransactionService;
	@Autowired
	CardRepository cardRepository;

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Logger logger = getGlobalLogger();
		Card clientCard = cardRepository.findById((long) 5).get();
//		pointTransactionService.createTransaction(
//				new TransactionDto().setCard(clientCard).setDebit(11));
		Integer i = pointTransactionService.getPoints(clientCard.getId());
		logger.log(Level.INFO, "Total existing points: "+ i);
		pointTransactionService.usePoints(7, clientCard.getId());
		i = pointTransactionService.getPoints(clientCard.getId());
		logger.log(Level.INFO, "Total existing points: "+ i);
		
	}

	private Logger getGlobalLogger() {
		return LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
	}

}
