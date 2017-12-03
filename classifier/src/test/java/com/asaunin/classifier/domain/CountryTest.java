package com.asaunin.classifier.domain;

import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class CountryTest {

    @Test
    public void whenNameIsEmptyRerunAnyCountry() {
        assertThat(Country.of("")).isEqualTo(AnyCountry.getInstance());

        assertTrue(Country.of("").isAnyCountry());
    }

    @Test
    public void whenNameIsNullRerunAnyCountry() {
        assertThat(Country.of(null)).isEqualTo(AnyCountry.getInstance());

        assertTrue(Country.of(null).isAnyCountry());
    }

    @Test
    public void whenNameIsPresentRerunRealCountry() {
        assertThat(Country.of("RU")).isEqualTo(new RealCountry("RU"));
        assertThat(Country.of("ru")).isEqualTo(new RealCountry("RU"));
        assertThat(Country.of("RU")).isEqualTo(new RealCountry("ru"));
        assertThat(Country.of("RU")).isNotEqualTo(new RealCountry("SN"));

        assertFalse(Country.of("RU").isAnyCountry());
    }

}