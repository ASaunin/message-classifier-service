package com.asaunin.cache;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

public class LoadableEntityCache<K, V, E> extends EntityCache<K, V, E> implements Loadable<E> {

    public LoadableEntityCache(Function<E, K> entityToKeyMapper, Function<E, V> entityToValueMapper) {
        super(entityToKeyMapper, entityToValueMapper);
    }

    @Override
    public void upload(Collection<E> data) {
        if (Objects.nonNull(data)) {
            data.forEach(this::put);
        }
    }

    @Override
    public void put(E entity) {
        final K key = entityToKeyMapper.apply(entity);
        final V value = entityToValueMapper.apply(entity);
        put(key, value);
    }

}
