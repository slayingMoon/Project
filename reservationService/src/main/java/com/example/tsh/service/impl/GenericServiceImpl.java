package com.example.tsh.service.impl;

import com.example.tsh.dao.GenericRepository;
import com.example.tsh.model.entity.BaseEntity;
import com.example.tsh.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;



public  class GenericServiceImpl< E extends BaseEntity> implements GenericService<E> {

@Autowired
  private GenericRepository<E> repository;



    @Override
    public E findEntityById(Long id) {

        return repository.findById(id).orElse(null);
    }

    @Override
    public E createOrUpdateEntity(E entity) {
       return repository.save(entity);
    }

    @Override
    public void delete(E entity) {
        repository.deleteById(entity.getId());
    }

}
