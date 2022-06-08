package com.tsh.clientManager.services.impl;

import com.tsh.clientManager.model.entities.SaleItem;
import com.tsh.clientManager.repository.SaleItemRepository;
import com.tsh.clientManager.services.SaleItemService;
import org.springframework.stereotype.Service;

@Service
public class SaleItemServiceImpl implements SaleItemService {

    private final SaleItemRepository saleItemRepository;

    public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
        this.saleItemRepository = saleItemRepository;
    }

    @Override
    public void persist(SaleItem saleItem) {
        saleItemRepository.save(saleItem);
    }
}
