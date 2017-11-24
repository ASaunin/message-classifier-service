package com.asaunin.cache;

import java.util.Collection;
import java.util.function.Function;

abstract class DeletableExtendedCache<K, V, E extends Deletable> extends ExtendedCache<K, V, E> implements Loadable<E> {

    public DeletableExtendedCache(Function<E, K> entityToKeyMapper, Function<E, V> entityToValueMapper) {
        super(entityToKeyMapper, entityToValueMapper);
    }

    @Override
    public void upload(Collection<E> data) {
        data.forEach(this::put);
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

    abstract void remove(K key, V value);

}
