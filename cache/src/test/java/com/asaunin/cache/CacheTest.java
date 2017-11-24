package com.asaunin.cache;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.ThrowableAssert.ThrowingCallable;

public class CacheTest extends Cache<String, String> {

    @Test
    public void putOperationsCheck() {
        final String key1 = "key1";
        final String key2 = "key2";
        final String value1 = "value1";
        final String value2 = "value2";

        assertThat(isEmpty()).isTrue();

        assertThat(put(key1, value1)).isNotPresent();
        assertThat(size()).isOne();
        assertThat(get(key1)).isPresent().contains(value1);
        assertThat(get(key2)).isNotPresent();

        assertThat(put(key1, value2)).isPresent().contains(value1);
        assertThat(get(key1)).isPresent().contains(value2);
        assertThat(get(key2)).isNotPresent();
        assertThat(size()).isOne();

        assertThat(put(key2, value2)).isNotPresent();
        assertThat(get(key1)).isPresent().contains(value2);
        assertThat(get(key2)).isPresent().contains(value2);
        assertThat(size()).isEqualTo(2);
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

        assertThat(remove(key3)).isNotPresent();
        assertThat(size()).isEqualTo(2);

        assertThat(remove(key2)).isPresent().contains(value2);
        assertThat(size()).isOne();
        assertThat(remove(key2)).isNotPresent();
        assertThat(size()).isOne();

        assertThat(remove(key1)).isPresent().contains(value1);
        assertThat(size()).isZero();
        assertThat(remove(key1)).isNotPresent();
        assertThat(size()).isZero();
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
        assertThat(get(key1)).isNotPresent();
        assertThat(get(key2)).isNotPresent();
    }

    @Test
    public void whenNullKeysOrValuesThanThrowNullPointerException() {
        assertThatNullPointerExceptionIsThrownBy(() -> put(null, ""));
        assertThatNullPointerExceptionIsThrownBy(() -> put(null, ""));
        assertThatNullPointerExceptionIsThrownBy(() -> put("", null));
        assertThatNullPointerExceptionIsThrownBy(() -> put(null, null));

        assertThatNullPointerExceptionIsThrownBy(() -> remove(null));

        assertThatNullPointerExceptionIsThrownBy(() -> get(null));
    }

    private void assertThatNullPointerExceptionIsThrownBy(ThrowingCallable throwingCallable) {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(throwingCallable);
    }

}