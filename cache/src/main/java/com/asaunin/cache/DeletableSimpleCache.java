package com.asaunin.cache;

import java.util.Collection;
import java.util.function.Function;

public class DeletableSimpleCache<K, V extends Deletable> extends DeletableExtendedCache<K, V, V> {

    public DeletableSimpleCache(Function<V, K> keyMapper) {
        super(keyMapper, Function.identity());
    }

    @Override
    public void upload(Collection<V> data) {
        data.forEach(this::put);
    }

    @Override
    void remove(K key, V value) {
        remove(key);
    }

}
