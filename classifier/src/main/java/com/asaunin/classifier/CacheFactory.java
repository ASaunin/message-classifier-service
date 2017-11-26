package com.asaunin.classifier;

import com.asaunin.cache.Cacheable;
import com.asaunin.classifier.repository.LoadableRepository;

import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Consumer;

public class CacheFactory<C extends Cacheable, R extends LoadableRepository> {

    private List<Class> types = new LinkedList<>();

    private Map<Class, C> caches = new LinkedHashMap<>();
    private Map<Class, R> repositories = new LinkedHashMap<>();
    private Map<Class, SimpleEntry<C, R>> pairs = new LinkedHashMap<>();

    public CacheFactory(Consumer<Builder<C, R>> builder) {
        builder.accept((type, cache, repo) -> {
            types.add(type);
            caches.putIfAbsent(type, cache);
            repositories.putIfAbsent(type, repo);
            pairs.putIfAbsent(type, new SimpleEntry(cache, repo));
        });
    }

    public Iterable<Class> getTypes() {
        return () -> types.iterator();
    }

    public C getCache(Class type) {
        return caches.get(type);
    }

    public Iterable<C> getCaches() {
        return caches.values();
    }

    public R getRepository(Class type) {
        return repositories.get(type);
    }

    public Iterable<R> getRepositories() {
        return repositories.values();
    }

    public SimpleEntry<C, R> getPair(Class type) {
        return pairs.get(type);
    }

    public Iterable<SimpleEntry<C, R>> getPairs() {
        return pairs.values();
    }

    @FunctionalInterface
    public interface Builder<C, R> {

        void add(Class clazz, C cache, R repository);

    }

}
