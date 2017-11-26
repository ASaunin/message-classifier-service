package com.asaunin.cache;

import java.util.Collection;
import java.util.function.Function;

public class DeletableCache<K, V extends Deletable> extends DeletableEntityCache<K, V, V> {

    public DeletableCache(Function<V, K> keyMapper) {
        super(keyMapper, Function.identity());
    }

    @Override
    public void upload(Collection<V> data) {
        data.forEach(this::put);
    }

    @Override
    protected void remove(K key, V value) {
        remove(key);
    }

}
