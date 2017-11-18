package com.asaunin.classifier;

import com.asaunin.cache.Cacheable;
import org.springframework.data.repository.Repository;
import org.springframework.data.util.Pair;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class CacheFactory<C extends Cacheable, R extends Repository> {

    private List<Class> types = new LinkedList<>();

    private Map<Class, C> caches = new LinkedHashMap<>();
    private Map<Class, R> repositories = new LinkedHashMap<>();
    private Map<Class, Pair<C, R>> pairs = new LinkedHashMap<>();

    public CacheFactory(Consumer<Builder<C, R>> builder) {
        builder.accept((type, cache, repo) -> {
            types.add(type);
            caches.putIfAbsent(type, cache);
            repositories.putIfAbsent(type, repo);
            pairs.putIfAbsent(type, Pair.of(cache, repo));
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

    public Pair<C, R> getPair(Class type) {
        return pairs.get(type);
    }

    public Iterable<Pair<C, R>> getPairs() {
        return pairs.values();
    }

    @FunctionalInterface
    public interface Builder<C, R> {

        void add(Class clazz, C cache, R repository);

    }

}
