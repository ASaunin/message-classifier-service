package com.asaunin.cache;

import java.util.function.Function;

public abstract class DeletableEntityCache<K, V, E extends Deletable> extends LoadableEntityCache<K, V, E> {

    public DeletableEntityCache(Function<E, K> entityToKeyMapper, Function<E, V> entityToValueMapper) {
        super(entityToKeyMapper, entityToValueMapper);
    }

    @Override
    public void put(E entity) {
        final K key = entityToKeyMapper.apply(entity);
        final V value = entityToValueMapper.apply(entity);
        if (entity.isDeleted()) {
            remove(key, value);
        } else {
            put(key, value);
        }
    }

    protected abstract void remove(K key, V value);

}
