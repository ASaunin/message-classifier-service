package com.asaunin.cache;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
public class Cache<K, V> implements Cacheable<K, V> {

    private final Map<K, V> cache = new ConcurrentHashMap<>();

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public boolean isEmpty() {
        return cache.isEmpty();
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return cache.containsKey(key);
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
