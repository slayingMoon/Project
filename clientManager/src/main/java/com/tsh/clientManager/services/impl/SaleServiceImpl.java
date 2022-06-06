package com.tsh.clientManager.services.impl;

import com.tsh.clientManager.repository.SaleRepository;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.services.SaleItemService;
import com.tsh.clientManager.services.SaleService;
import org.springframework.stereotype.Service;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;
    private final ClientService clientService;
    private final CardService cardService;

    public SaleServiceImpl(SaleRepository saleRepository, SaleItemService saleItemService, ClientService clientService, CardService cardService) {
        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
        this.clientService = clientService;
        this.cardService = cardService;
    }


    @Override
    public void createSale() {

    }

}
