package com.tsh.clientManager.services;

import com.tsh.clientManager.model.dto.SaleDto;
import com.tsh.clientManager.model.entities.Sale;

public interface SaleService {

    Sale createSale(SaleDto saleDto);

    Sale findSaleByClient(String phoneNumber);
}
