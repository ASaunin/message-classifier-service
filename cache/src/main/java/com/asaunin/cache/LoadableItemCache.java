package com.asaunin.cache;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

public abstract class LoadableItemCache<K, V, I, E> extends ItemCache<K, V, I, E> implements Loadable<E> {

    public LoadableItemCache(Map<K, V> cache) {
        this(cache, false);
    }

    public LoadableItemCache(Map<K, V> cache, boolean permitNullCache) {
        super(cache, permitNullCache);
    }

    @Override
    public void insertAll(Collection<E> data) {
        if (Objects.nonNull(data)) {
            data.forEach(this::insert);
        }
    }

    @Override
    public V insert(E entity) {
        final K key = mapToKey(entity);
        final I item = mapToItem(entity);
        return insert(key, item);
    }

}
