package com.asaunin.cache;

import java.util.function.Function;

public class SimpleCache<K, V> extends ExtendedCache<K, V, V> {

    public SimpleCache(Function<V, K> entityToKeyMapper) {
        super(entityToKeyMapper, Function.identity());
    }

    public void put(V entity) {
        final K key = entityToKeyMapper.apply(entity);
        put(key, entity);
    }

}
