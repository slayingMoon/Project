package com.tsh.frantishex.clientManagerService.services;

import com.tsh.frantishex.clientManagerService.model.dto.SaleDto;
import com.tsh.frantishex.clientManagerService.model.dto.SaleSaleItemDto;
import com.tsh.frantishex.clientManagerService.model.entities.Sale;
import com.tsh.frantishex.clientManagerService.model.entities.SaleItem;
import com.tsh.frantishex.clientManagerService.repository.SaleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleItemService saleItemService;
    private final ClientService clientService;
    private final CardService cardService;
    private final ModelMapper modelMapper;

    public SaleService(SaleRepository saleRepository, SaleItemService saleItemService, ClientService clientService, CardService cardService, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.saleItemService = saleItemService;
        this.clientService = clientService;
        this.cardService = cardService;
        this.modelMapper = modelMapper;
    }


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

    public Sale findSaleByClient(String phoneNumber) {
        return saleRepository.findFirstByClientPhoneNumber(phoneNumber);
    }
}
