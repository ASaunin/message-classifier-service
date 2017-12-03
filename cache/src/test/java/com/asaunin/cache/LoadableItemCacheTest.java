package com.asaunin.cache;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static com.asaunin.cache.LoadableItemCacheTest.LoadableListedCache.EVEN;
import static com.asaunin.cache.LoadableItemCacheTest.LoadableListedCache.ODD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class LoadableItemCacheTest {

    private final LoadableItemCache<Boolean, List<String>, String, Integer> cache =
            new LoadableListedCache(new HashMap<>());

    @Test
    public void uploadOperationsCheck() {
        final List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        cache.insertAll(data);

        final List<String> oddList = cache.get(ODD);
        final List<String> evenList = cache.get(EVEN);
        assertThat(evenList).containsExactlyInAnyOrder("2", "4");
        assertThat(oddList).containsExactlyInAnyOrder("1", "3", "5");
    }

    @Test
    public void whenUploadingDataIsNullDoNothing() {
        cache.insertAll(null);
        assertThat(cache.size()).isZero();
    }

    @Test
    public void whenUploadingDataContainsNullValuesThanThrowNullPointerException() {
        final List<Integer> data = Arrays.asList(1, 2, null, 4);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> cache.insertAll(data));
    }

    protected static class LoadableListedCache extends LoadableItemCache<Boolean, List<String>, String, Integer> {

        final static boolean ODD = false;
        final static boolean EVEN = true;

        private final static Function<Integer, Boolean> evenOdd = entity -> (entity & 1) == 0;

        private final static Function<Integer, String> toString = String::valueOf;

        LoadableListedCache(Map<Boolean, List<String>> cache) {
            super(cache);
        }

        @Override
        protected Boolean mapToKey(Integer entity) {
            return evenOdd.apply(entity);
        }

        @Override
        protected String mapToItem(Integer entity) {
            return toString.apply(entity);
        }

        @Override
        protected List<String> insert(Boolean key, String item) {
            List<String> list = get(key);
            if (Objects.isNull(list)) {
                list = new ArrayList<>();
                put(key, list);
            }
            list.add(item);
            return list;
        }

    }

}