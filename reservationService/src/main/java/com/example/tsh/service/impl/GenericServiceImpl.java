package com.example.tsh.service.impl;

import com.example.tsh.dao.GenericRepository;
import com.example.tsh.model.entity.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;


public  class GenericServiceImpl< E extends BaseEntity>  {

@Autowired
  protected GenericRepository<E> repository;




    public E findEntityById(Long id) {

        return repository.findById(id).orElse(null);
    }


    @Transactional
    public E createOrUpdateEntity(E entity) {
       return repository.save(entity);
    }


    public void delete(E entity) {
        repository.deleteById(entity.getId());
    }

}
