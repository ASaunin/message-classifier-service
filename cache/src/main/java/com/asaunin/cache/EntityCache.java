package com.asaunin.cache;

import lombok.RequiredArgsConstructor;

import java.util.function.Function;

@RequiredArgsConstructor
public class EntityCache<K, V, E> extends Cache<K, V> {

    public final Function<E, K> entityToKeyMapper;
    public final Function<E, V> entityToValueMapper;

}
