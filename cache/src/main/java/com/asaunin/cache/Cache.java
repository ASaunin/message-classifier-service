package com.asaunin.cache;

import java.util.Map;
import java.util.Objects;

public class Cache<K, V> implements Cacheable<K, V> {

    private final Map<K, V> cache;
    private final boolean permitNullCache;

    public Cache(Map<K, V> cache) {
        this(cache, false);
    }

    public Cache(Map<K, V> cache, boolean permitNullCache) {
        this.cache = cache;
        this.permitNullCache = permitNullCache;
    }

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
        checkNullKey(key);
        return cache.containsKey(key);
    }

    protected V put(K key, V value) {
        checkNullKeyValue(key, value);
        return cache.put(key, value);
    }

    @Override
    public V get(K key) {
        checkNullKey(key);
        return cache.get(key);
    }

    @Override
    public V remove(K key) {
        checkNullKey(key);
        return cache.remove(key);
    }

    private void checkNullKey(K key) {
        if (Objects.isNull(key) && !permitNullCache) {
            throw new NullPointerException("Null key is not permitted for this type of cache. " +
                    "Try to use NULL-object design pattern instead!");
        }
    }

    private void checkNullKeyValue(K key, V value) {
        checkNullKey(key);
        if (Objects.isNull(value) && !permitNullCache) {
            throw new NullPointerException("Null value is not permitted for this type of cache. " +
                    "Try to use NULL-object design pattern instead!");
        }
    }

}
