package com.asaunin.cache;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class LoadableTest {

    private final LoadableList list = new LoadableList();

    @Before
    public void clearCache() {
        list.clear();
        assertTrue(list.isEmpty());
        assertThat(list.size()).isZero();
    }

    @Test
    public void whenNoDataDoNothing() {
        list.add(1);
        list.add(2);

        list.insertAll(null);
        assertThat(list).containsExactlyInAnyOrder(1, 2);

        list.insertAll(new ArrayList<>());
        assertThat(list).containsExactlyInAnyOrder(1, 2);
    }

    public static class LoadableList extends ArrayList<Integer> implements Loadable<Integer> {

        @Override
        public Object insert(Integer item) {
            return add(item);
        }

    }

}