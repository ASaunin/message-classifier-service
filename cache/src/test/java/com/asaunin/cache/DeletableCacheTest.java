package com.asaunin.cache;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static com.asaunin.cache.DeletableCacheTest.DeletableCacheImpl.EVEN;
import static com.asaunin.cache.DeletableCacheTest.DeletableCacheImpl.ODD;
import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class DeletableCacheTest {

    private final DeletableCache<Boolean, DeletableInteger> cache = new DeletableCacheImpl(new HashMap<>());

    @Before
    public void clearCache() {
        cache.clear();
        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isZero();
    }

    @Test
    public void checkInsertOperation() {
        // Insert 1
        final DeletableInteger one = new DeletableInteger(1);
        cache.insert(one);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(ODD)).isEqualTo(one);

        // Insert 2
        final DeletableInteger two = new DeletableInteger(2);
        cache.insert(two);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).isEqualTo(one);
        assertThat(cache.get(EVEN)).isEqualTo(two);

        // Insert 3
        final DeletableInteger three = new DeletableInteger(3);
        cache.insert(three);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).isEqualTo(three);
        assertThat(cache.get(EVEN)).isEqualTo(two);

        // Insert 4
        final DeletableInteger four = new DeletableInteger(4);
        cache.insert(four);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).isEqualTo(three);
        assertThat(cache.get(EVEN)).isEqualTo(four);
    }

    @Test
    public void checkInsertDeletedOperation() {
        // Insert 1
        final DeletableInteger one = new DeletableInteger(1);
        cache.insert(one);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(ODD)).isEqualTo(one);

        // Insert 2
        final DeletableInteger two = new DeletableInteger(2);
        cache.insert(two);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).isEqualTo(one);
        assertThat(cache.get(EVEN)).isEqualTo(two);

        // Insert 3 deleted
        final DeletableInteger three = new DeletableInteger(3, true);
        cache.insert(three);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(ODD)).isNull();
        assertThat(cache.get(EVEN)).isEqualTo(two);

        // Insert 4 deleted
        final DeletableInteger four = new DeletableInteger(4, true);
        cache.insert(four);

        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(0);
        assertThat(cache.get(ODD)).isNull();
        assertThat(cache.get(EVEN)).isNull();
    }

    protected static class DeletableCacheImpl extends DeletableCache<Boolean, DeletableInteger> {

        final static boolean ODD = false;
        final static boolean EVEN = true;

        private final static Function<DeletableInteger, Boolean> evenOdd = entity -> (entity.intValue() & 1) == 0;

        public DeletableCacheImpl(Map<Boolean, DeletableInteger> cache) {
            super(cache);
        }

        @Override
        protected Boolean mapToKey(DeletableInteger entity) {
            return evenOdd.apply(entity);
        }

    }

    protected static class DeletableInteger extends Number implements Deletable {

        private final boolean deleted;
        private final Integer value;

        public DeletableInteger(Integer value) {
            this(value, false);
        }

        public DeletableInteger(Integer value, boolean deleted) {
            this.value = value;
            this.deleted = deleted;
        }

        @Override
        public boolean isDeleted() {
            return deleted;
        }

        @Override
        public int intValue() {
            return value;
        }

        @Override
        public long longValue() {
            return value;
        }

        @Override
        public float floatValue() {
            return value;
        }

        @Override
        public double doubleValue() {
            return value;
        }

    }

}