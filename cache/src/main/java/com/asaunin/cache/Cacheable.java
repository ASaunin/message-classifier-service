package com.asaunin.cache;

@SuppressWarnings("SpellCheckingInspection")
public interface Cacheable<K, V> {

    int size();

    boolean isEmpty();

    void clear();

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

}
