package com.tsh.clientManager;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tsh.clientManager.model.dto.CardDto;
import com.tsh.clientManager.model.dto.ClientPhoneNumberDto;
import com.tsh.clientManager.services.CardService;

@Component
public class CreateCardDemo implements CommandLineRunner {

    private CardService cardService;

    public CreateCardDemo(CardService cardService) {
        this.cardService = cardService;
    }

    @Override
    public void run(String... args) throws Exception {

//        cardService.createCard(new CardDto()
//                .setBalance(0)
//                .setClient(new ClientPhoneNumberDto().setPhoneNumber("+359895878111")));
//                .setClient(new ClientPhoneNumberDto().setPhoneNumber("+359888777999")));
    }
}
