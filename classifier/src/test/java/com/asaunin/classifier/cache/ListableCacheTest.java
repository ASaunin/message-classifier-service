package com.asaunin.classifier.cache;

import com.asaunin.classifier.domain.DeletableEntity;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static com.asaunin.classifier.cache.ListableCacheTest.ListableCacheImpl.EVEN;
import static com.asaunin.classifier.cache.ListableCacheTest.ListableCacheImpl.ODD;
import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class ListableCacheTest {

    private final ListableCache<Boolean, DeletableInteger> cache = new ListableCacheImpl(new HashMap<>());

    @Before
    public void clearCache() {
        cache.clear();
        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isZero();
        assertThat(cache.get(ODD)).isNull();
        assertThat(cache.get(EVEN)).isNull();
    }

    @Test
    public void checkInsertOperation() {
        // Insert 1
        final DeletableInteger one = new DeletableInteger(1);
        cache.insert(one);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(ODD)).containsExactly(one);
        assertThat(cache.get(EVEN)).isNull();

        // Insert 2
        final DeletableInteger two = new DeletableInteger(2);
        cache.insert(two);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).containsExactly(one);
        assertThat(cache.get(EVEN)).containsExactly(two);

        // Insert 3
        final DeletableInteger three = new DeletableInteger(3);
        cache.insert(three);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).containsExactly(one, three);
        assertThat(cache.get(EVEN)).containsExactly(two);

        // Insert 4
        final DeletableInteger four = new DeletableInteger(4);
        cache.insert(four);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).containsExactly(one, three);
        assertThat(cache.get(EVEN)).containsExactly(two, four);
    }

    @Test
    public void checkInsertDeletedOperation() {
        // Insert 1
        DeletableInteger one = new DeletableInteger(1);
        cache.insert(one);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(1);
        assertThat(cache.get(ODD)).containsExactly(one);
        assertThat(cache.get(EVEN)).isNull();

        // Insert 2
        DeletableInteger two = new DeletableInteger(2);
        cache.insert(two);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).containsExactly(one);
        assertThat(cache.get(EVEN)).containsExactly(two);

        // Insert 3 deleted
        final DeletableInteger three = new DeletableInteger(3, true);
        cache.insert(three);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).containsExactly(one);
        assertThat(cache.get(EVEN)).containsExactly(two);

        // Insert 4 deleted
        final DeletableInteger four = new DeletableInteger(4, true);
        cache.insert(four);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).containsExactly(one);
        assertThat(cache.get(EVEN)).containsExactly(two);

        // Insert 1 deleted
        one = new DeletableInteger(1, true);
        cache.insert(one);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).isEmpty();
        assertThat(cache.get(EVEN)).containsExactly(two);

        // Insert 2 deleted
        two = new DeletableInteger(2, true);
        cache.insert(two);

        assertFalse(cache.isEmpty());
        assertThat(cache.size()).isEqualTo(2);
        assertThat(cache.get(ODD)).isEmpty();
        assertThat(cache.get(EVEN)).isEmpty();
    }

    protected static class ListableCacheImpl extends ListableCache<Boolean, DeletableInteger> {

        final static boolean ODD = false;
        final static boolean EVEN = true;

        private final static Function<DeletableInteger, Boolean> evenOdd = entity -> (entity.getValue() & 1) == 0;

        public ListableCacheImpl(Map<Boolean, List<DeletableInteger>> cache) {
            super(cache);
        }

        @Override
        protected Boolean mapToKey(DeletableInteger entity) {
            return evenOdd.apply(entity);
        }

    }

    @Value
    @EqualsAndHashCode(callSuper = true)
    protected static class DeletableInteger extends DeletableEntity {

        private final Integer value;

        public DeletableInteger(Integer value) {
            this(value, false);
        }

        public DeletableInteger(Integer value, boolean deleted) {
            super(deleted);
            this.value = value;
        }

    }

}