package com.tsh.clientManager.services.impl;

import com.tsh.clientManager.model.dto.SaleDto;
import com.tsh.clientManager.model.dto.SaleSaleItemDto;
import com.tsh.clientManager.model.entities.Sale;
import com.tsh.clientManager.model.entities.SaleItem;
import com.tsh.clientManager.repository.SaleRepository;
import com.tsh.clientManager.services.CardService;
import com.tsh.clientManager.services.ClientService;
import com.tsh.clientManager.services.SaleItemService;
import com.tsh.clientManager.services.SaleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ClientService clientService;
    private final CardService cardService;
    private final ModelMapper modelMapper;
    private final SaleItemService saleItemService;

    public SaleServiceImpl(SaleRepository saleRepository, ClientService clientService, CardService cardService, ModelMapper modelMapper, SaleItemService saleItemService) {
        this.saleRepository = saleRepository;
        this.clientService = clientService;
        this.cardService = cardService;
        this.modelMapper = modelMapper;
        this.saleItemService = saleItemService;
    }

    @Override
    public Sale createSale(SaleDto saleDto) {

        Sale sale = new Sale()
                .setTotalPrice(saleDto.getTotalPrice())
                .setCard(cardService.findCardById(saleDto.getCard().getId()))
                .setClient(clientService.findClientByPhoneNumber(saleDto.getClient().getPhoneNumber()));

        List<SaleSaleItemDto> saleItems = saleDto.getSaleItems();

        saleRepository.save(sale);

        saleItems.forEach(saleItemDto -> {

            SaleItem saleItem = modelMapper.map(saleItemDto, SaleItem.class);

            saleItem.setSale(sale);
            saleItemService.persist(saleItem);
        });

        return sale;
    }

    @Override
    public Sale findSaleByClient(String phoneNumber) {
        return saleRepository.findFirstByClientPhoneNumber(phoneNumber);
    }
}
