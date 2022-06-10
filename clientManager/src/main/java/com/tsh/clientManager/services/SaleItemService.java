package com.tsh.clientManager.services;
import com.tsh.clientManager.model.entities.SaleItem;
import com.tsh.clientManager.repository.SaleItemRepository;
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
