package com.asaunin.cache;

import java.util.Collection;

@FunctionalInterface
public interface Loadable<E>  {

    default void upload(Collection<E> data) {
        if (data == null || data.isEmpty()) {
            return;
        }

        data.forEach(this::put);
    }

    void put(E e);

}
