package com.asaunin.classifier.cache;

import com.asaunin.cache.DeletableItemCache;
import com.asaunin.classifier.domain.DeletableEntity;

import java.util.*;

public abstract class ListableCache<K, V extends DeletableEntity> extends DeletableItemCache<K, List<V>, V, V> {

    public ListableCache(Map<K, List<V>> cache) {
        this(cache, false);
    }

    public ListableCache(Map<K, List<V>> cache, boolean permitNullCache) {
        super(cache, permitNullCache);
    }

    @Override
    protected V mapToItem(V entity) {
        return entity;
    }

    @Override
    protected List<V> insert(K key, V item) {
        final List<V> list;
        if (containsKey(key)) {
            list = get(key);
        } else {
            list = new ArrayList<>();
            put(key, list);
        }
        list.add(item);
        return list;
    }

    @Override
    protected List<V> exclude(K key, V item) {
        List<V> list = get(key);
        if (Objects.nonNull(list)) {
            list.remove(item);
        }
        return list;
    }

}
