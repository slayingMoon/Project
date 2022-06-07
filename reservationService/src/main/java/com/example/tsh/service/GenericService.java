package com.example.tsh.service;

import com.example.tsh.model.entity.BaseEntity;

public interface GenericService <D extends BaseEntity>{
    D findEntityById(Long id);

    D createOrUpdateEntity(D dto);


}
