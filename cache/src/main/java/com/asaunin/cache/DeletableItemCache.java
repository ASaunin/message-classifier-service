package com.asaunin.cache;

import java.util.Map;

public abstract class DeletableItemCache<K, V, I, E extends Deletable> extends LoadableItemCache<K, V, I, E> {

    public DeletableItemCache(Map<K, V> cache) {
        this(cache, false);
    }

    public DeletableItemCache(Map<K, V> cache, boolean permitNullCache) {
        super(cache, permitNullCache);
    }

    @Override
    public V insert(E entity) {
        final K key = mapToKey(entity);
        final I item = mapToItem(entity);
        if (entity.isDeleted()) {
            return exclude(key, item);
        } else {
            return insert(key, item);
        }
    }

    protected abstract V exclude(K key, I item);

}
