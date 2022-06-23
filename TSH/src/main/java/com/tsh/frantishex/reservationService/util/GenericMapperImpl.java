package com.tsh.frantishex.reservationService.util;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public  class GenericMapperImpl<P , E> implements GenericMapper<P, E>{

//    @Autowired
    private EntityManager entityManager;
    @Override
    public E map(P from, Class<E> to) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String s = to.getName();
        String s1 = to.getSimpleName();
        E e = to.getDeclaredConstructor().newInstance();
        List<Field> classFields = Arrays.asList(e.getClass().getDeclaredFields());
        List<Field> pojoFields = Arrays.asList(from.getClass().getDeclaredFields());
        System.out.println(classFields.size());
        System.out.println(pojoFields.size());

        for (Field pojoField : pojoFields) {
            for (Field field : classFields) {
                if(pojoField.getType().equals(field.getType()) && pojoField.getName().equals(field.getName())){
                    List<String> entities = entityManager.getMetamodel().getEntities().stream().map(Object::toString).collect(Collectors.toList());
                    if(entities.contains(field.getType().getSimpleName())){
                        continue;
                    }
                    field.setAccessible(true);
                    pojoField.setAccessible(true);
                    field.set(e, pojoField.get(from));
                    break;
                }
            }
        }
        System.out.println();
        return e;
    }

}
