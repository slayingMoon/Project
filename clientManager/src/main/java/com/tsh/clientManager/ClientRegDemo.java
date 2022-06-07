package com.tsh.clientManager;

import com.tsh.clientManager.model.dto.RegisterClientDto;
import com.tsh.clientManager.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientRegDemo implements CommandLineRunner {

    private ClientService clientService;

    public ClientRegDemo(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void run(String... args) throws Exception {

//        clientService.clientRegistration(new RegisterClientDto()

//                .setFirstName("Pesho")
//                .setLastName("Petkov")
//                .setAge(20)
//                .setPhoneNumber("+359895878111")
//                .setEmail("pesho.petkov@gmail.com"));

//                .setFirstName("Kaloyan")
//                .setLastName("Bogoev")
//                .setAge(33)
//                .setPhoneNumber("+359888777155")
//                .setEmail("kala.bo@gmail.com"));

    }
}
