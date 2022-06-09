package com.tsh.clientManager.services;

import org.springframework.stereotype.Service;

import com.tsh.clientManager.repository.SaleRepository;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;
    private final ClientService clientService;
    private final CardService cardService;

    public SaleService(SaleRepository saleRepository, SaleItemService saleItemService, ClientService clientService, CardService cardService) {
        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
        this.clientService = clientService;
        this.cardService = cardService;
    }


    public void createSale() {

    }
}
