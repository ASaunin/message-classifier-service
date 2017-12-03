package com.asaunin.cache;

import java.util.Map;

public abstract class LoadableCache<K, V> extends LoadableItemCache<K, V, V, V> {

    public LoadableCache(Map<K, V> cache) {
        this(cache, false);
    }

    public LoadableCache(Map<K, V> cache, boolean permitNullCache) {
        super(cache, permitNullCache);
    }

    @Override
    protected V mapToItem(V entity) {
        return entity;
    }

    @Override
    protected V insert(K key, V item) {
        put(key, item);
        return item;
    }

}
