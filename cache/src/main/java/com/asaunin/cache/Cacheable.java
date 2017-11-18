package com.asaunin.cache;

import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
public interface Cacheable<K, V> {

    int size();

    void clear();

    Optional<V> put(K key, V value);

    Optional<V> get(K key);

    Optional<V> remove(K key);

}
