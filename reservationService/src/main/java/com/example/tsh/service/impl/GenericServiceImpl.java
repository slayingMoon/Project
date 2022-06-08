package com.example.tsh.service.impl;

import com.example.tsh.dao.GenericRepository;
import com.example.tsh.model.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;



public  class GenericServiceImpl< E extends BaseEntity>  {

@Autowired
  private GenericRepository<E> repository;




    public E findEntityById(Long id) {

        return repository.findById(id).orElse(null);
    }


    public E createOrUpdateEntity(E entity) {
       return repository.save(entity);
    }


    public void delete(E entity) {
        repository.deleteById(entity.getId());
    }

}
