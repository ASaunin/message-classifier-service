package com.asaunin.cache;

import java.util.function.Function;

public class LoadableCache<K, V> extends LoadableEntityCache<K, V, V> {

    public LoadableCache(Function<V, K> entityToKeyMapper) {
        super(entityToKeyMapper, Function.identity());
    }

    @Override
    public void put(V entity) {
        final K key = entityToKeyMapper.apply(entity);
        put(key, entity);
    }

}
