package com.asaunin.cache;

import java.util.Map;

public abstract class DeletableCache<K, V extends Deletable> extends DeletableItemCache<K, V, V, V> {

    public DeletableCache(Map<K, V> cache) {
        this(cache, false);
    }

    public DeletableCache(Map<K, V> cache, boolean permitNullCache) {
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

    @Override
    protected V exclude(K key, V item) {
        remove(key);
        return null;
    }

}
