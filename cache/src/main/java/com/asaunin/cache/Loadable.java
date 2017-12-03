package com.asaunin.cache;

import java.util.Collection;

@FunctionalInterface
public interface Loadable<E>  {

    default void insertAll(Collection<E> data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        data.forEach(this::insert);
    }

    Object insert(E e);

}
