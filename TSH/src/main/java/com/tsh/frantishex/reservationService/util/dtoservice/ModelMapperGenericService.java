package com.tsh.frantishex.reservationService.util.dtoservice;

import com.tsh.frantishex.reservationService.model.dto.BaseDto;

public interface ModelMapperGenericService<D extends BaseDto> {
    D findEntityById(Long id);

    void createOrUpdateEntity(D dto);
}
