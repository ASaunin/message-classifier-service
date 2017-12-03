package com.asaunin.cache;

import java.util.Map;

public abstract class ItemCache<K, V, I, E> extends Cache<K, V> {

    public ItemCache(Map<K, V> cache) {
        this(cache, false);
    }

    public ItemCache(Map<K, V> cache, boolean permitNullCache) {
        super(cache, permitNullCache);
    }

    protected abstract K mapToKey(E entity);

    protected abstract I mapToItem(E entity);

    protected abstract V insert(K key, I item);

}
