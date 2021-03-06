package com.tsh.frantishex.clientManagerService.services;
import com.tsh.frantishex.clientManagerService.model.entities.SaleItem;
import com.tsh.frantishex.clientManagerService.repository.SaleItemRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemService(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    public void persist(SaleItem saleItem) {
        saleItemRepository.save(saleItem);
    }

}
