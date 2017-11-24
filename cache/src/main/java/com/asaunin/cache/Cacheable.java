package com.asaunin.cache;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

@SuppressWarnings("SpellCheckingInspection")
public interface Cacheable<K, V> {

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    void clear();

    Optional<V> put(K key, V value);

    Optional<V> get(K key);

    Optional<V> remove(K key);

}
