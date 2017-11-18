package com.asaunin.cache;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public abstract class Cache<K, V> implements Cacheable<K, V> {

    final Map<K, V> cache = new ConcurrentHashMap<>();

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public Optional<V> put(K key, V value) {
        return Optional.ofNullable(cache.put(key, value));
    }

    @Override
    public Optional<V> get(K key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public Optional<V> remove(K key) {
        return Optional.ofNullable(cache.remove(key));
    }

}
