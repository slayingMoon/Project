package com.tsh.frantishex.reservationService.util;

import java.lang.reflect.InvocationTargetException;

public interface GenericMapper <P ,E> {
    E map(P from, Class<E> to) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
