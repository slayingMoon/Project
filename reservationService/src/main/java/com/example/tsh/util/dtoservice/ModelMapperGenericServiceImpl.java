package com.example.tsh.util.dtoservice;

import com.example.tsh.dao.GenericRepository;
import com.example.tsh.model.dto.BaseDto;
import com.example.tsh.model.entity.BaseEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

public class ModelMapperGenericServiceImpl <D extends BaseDto, E extends BaseEntity> implements ModelMapperGenericService<D> {

    @Autowired
    private GenericRepository<E> repository;

    @Autowired
    private ModelMapper modelMapper;


    private final Class<D> dtoClass;

    private final Class<E> entityClass;

    public ModelMapperGenericServiceImpl (Class<D> dtoClass, Class<E> entityClass) {
        this.dtoClass = dtoClass;
        this.entityClass = entityClass;
    }


    @Override
    public D findEntityById(Long id) {
        D dto = null;
        try {
            dto = dtoClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        E entity = repository.findById(id).orElse(null);
        modelMapper.map(entity, dto);
        return dto;
    }

    @Override
    public void createOrUpdateEntity(D dto) {
        E entity = null;
        try {
            entity = entityClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        modelMapper.map(dto, entity);
        repository.save(entity);

    }
}
