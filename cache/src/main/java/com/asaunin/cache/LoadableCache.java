package com.asaunin.cache;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class LoadableCache<K, V, T> extends Cache<K, V> {

    private final Function<T, K> keyMapper;
    private final Function<T, V> valueMapper;

    public void load(Collection<T> data) {
        cache.putAll(data.stream().collect(Collectors.toMap(keyMapper, valueMapper)));
    }

}
