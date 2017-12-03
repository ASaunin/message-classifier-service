package com.asaunin.cache;

import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public class CacheTest extends Cache<String, String> {

    public CacheTest() {
        super(new HashMap<>());
    }

    @Test
    public void putOperationsCheck() {
        final String key1 = "key1";
        final String key2 = "key2";
        final String value1 = "value1";
        final String value2 = "value2";

        assertThat(isEmpty()).isTrue();
        assertThat(containsKey(key1)).isFalse();
        assertThat(containsKey(key2)).isFalse();

        assertThat(put(key1, value1)).isNull();
        assertThat(size()).isOne();
        assertThat(get(key1)).isNotNull().contains(value1);
        assertThat(get(key2)).isNull();
        assertThat(containsKey(key1)).isTrue();
        assertThat(containsKey(key2)).isFalse();

        assertThat(put(key1, value2)).isNotNull().contains(value1);
        assertThat(get(key1)).isNotNull().contains(value2);
        assertThat(get(key2)).isNull();
        assertThat(size()).isOne();

        assertThat(put(key2, value2)).isNull();
        assertThat(get(key1)).isNotNull().contains(value2);
        assertThat(get(key2)).isNotNull().contains(value2);
        assertThat(size()).isEqualTo(2);
        assertThat(containsKey(key1)).isTrue();
        assertThat(containsKey(key2)).isTrue();
    }

    @Test
    public void removeOperationsCheck() {
        final String key1 = "key1";
        final String key2 = "key2";
        final String key3 = "key3";
        final String value1 = "value1";
        final String value2 = "value2";

        put(key1, value1);
        put(key2, value2);
        assertThat(size()).isEqualTo(2);
        assertThat(containsKey(key1)).isTrue();
        assertThat(containsKey(key2)).isTrue();
        assertThat(containsKey(key3)).isFalse();

        assertThat(remove(key3)).isNull();
        assertThat(size()).isEqualTo(2);
        assertThat(containsKey(key1)).isTrue();
        assertThat(containsKey(key2)).isTrue();
        assertThat(containsKey(key3)).isFalse();

        assertThat(remove(key2)).isNotNull().contains(value2);
        assertThat(size()).isOne();
        assertThat(remove(key2)).isNull();
        assertThat(size()).isOne();
        assertThat(containsKey(key1)).isTrue();
        assertThat(containsKey(key2)).isFalse();
        assertThat(containsKey(key3)).isFalse();

        assertThat(remove(key1)).isNotNull().contains(value1);
        assertThat(size()).isZero();
        assertThat(remove(key1)).isNull();
        assertThat(size()).isZero();
        assertThat(containsKey(key1)).isFalse();
        assertThat(containsKey(key2)).isFalse();
        assertThat(containsKey(key3)).isFalse();
    }

    @Test
    public void clearOperationsCheck() {
        final String key1 = "key1";
        final String key2 = "key2";
        final String value1 = "value1";
        final String value2 = "value2";

        put(key1, value1);
        put(key2, value2);
        assertThat(size()).isEqualTo(2);

        clear();
        assertThat(size()).isZero();
        assertThat(get(key1)).isNull();
        assertThat(get(key2)).isNull();
    }

    @Test
    public void whenNullKeysOrValuesThanThrowNullPointerException() {
        assertThatNullPointerExceptionIsThrownBy(() -> put(null, ""));
        assertThatNullPointerExceptionIsThrownBy(() -> put("", null));
        assertThatNullPointerExceptionIsThrownBy(() -> put(null, null));

        assertThatNullPointerExceptionIsThrownBy(() -> remove(null));

        assertThatNullPointerExceptionIsThrownBy(() -> get(null));
    }

    @Test
    public void whenNullKeysOrValuesArePermittedThanNoExceptions() {
        final Cache<String, String> cache = new Cache<>(new HashMap<>(), true);

        assertThatCode(() -> cache.put(null, "")).doesNotThrowAnyException();
        assertThatCode(() -> cache.put("", null)).doesNotThrowAnyException();
        assertThatCode(() -> cache.put(null, null)).doesNotThrowAnyException();
        assertThatCode(() -> cache.remove(null)).doesNotThrowAnyException();
        assertThatCode(() -> cache.get(null)).doesNotThrowAnyException();
    }

    private void assertThatNullPointerExceptionIsThrownBy(ThrowingCallable throwingCallable) {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(throwingCallable);
    }

}