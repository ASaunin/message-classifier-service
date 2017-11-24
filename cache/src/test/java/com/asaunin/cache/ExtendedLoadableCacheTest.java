package com.asaunin.cache;

import org.junit.Test;

import java.util.*;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class ExtendedLoadableCacheTest extends ExtendedCache<Boolean, List<String>, Integer> implements Loadable<Integer> {

    public ExtendedLoadableCacheTest() {
        super(evenOdd, toList);
    }

    private static Function<Integer, Boolean> evenOdd = entity -> (entity & 1) == 0;

    private static Function<Integer, List<String>> toList =
            entity -> {
                final ArrayList<String> list = new ArrayList<>();
                list.add(String.valueOf(entity));
                return list;
            };

    @Override
    public void put(Integer entity) {
        final Boolean key = evenOdd.apply(entity);
        final List<String> value = toList.apply(entity);
        if (containsKey(key)) {
            get(key).ifPresent(v -> v.addAll(value));
        } else {
            put(key, value);
        }
    }

    @Test
    public void uploadOperationsCheck() {
        final List<Integer> data = Arrays.asList(1, 2, 3, 4, 5);
        upload(data);

        final List<String> oddList = get(true).get();
        final List<String> evenList = get(false).get();
        assertThat(oddList).containsExactlyInAnyOrder("2", "4");
        assertThat(evenList).containsExactlyInAnyOrder("1", "3", "5");
    }

    @Test
    public void whenUploadingDataIsNullDoNothing() {
        upload(null);
        assertThat(size()).isZero();
    }

    @Test
    public void whenUploadingDataContainsNullValuesThanThrowNullPointerException() {
        final List<Integer> data = Arrays.asList(1, 2, null, 4);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> upload(data));
    }
}