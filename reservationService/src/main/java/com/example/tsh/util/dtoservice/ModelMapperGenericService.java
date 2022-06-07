package com.example.tsh.util.dtoservice;

import com.example.tsh.model.dto.BaseDto;

public interface ModelMapperGenericService<D extends BaseDto> {
    D findEntityById(Long id);

    void createOrUpdateEntity(D dto);
}
