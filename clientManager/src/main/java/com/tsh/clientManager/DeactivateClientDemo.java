package com.tsh.clientManager;

import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.model.dto.RegisterClientDto;
import com.tsh.clientManager.model.entities.Client;
import com.tsh.clientManager.services.ClientService;

@Component
public class DeactivateClientDemo implements CommandLineRunner {

	@Autowired
	ClientService clientService;
	@Autowired
	EntityManager entityManager;

	@Override
	public void run(String... args) throws Exception {
		Logger logger = getGlobalLogger();
		Client newClient = clientService
				.findClientByPhoneNumber("+359886666666");
		boolean check = entityManager.contains(newClient);
		logger.log(Level.INFO, newClient.getStatus().toString());
		clientService.clientDeactivation(new ClientPhoneNumberDto().setPhoneNumber(newClient.getPhoneNumber()));
		logger.log(Level.INFO, newClient.getStatus().toString());
	}

	private Logger getGlobalLogger() {
		return LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME);
	}

}
