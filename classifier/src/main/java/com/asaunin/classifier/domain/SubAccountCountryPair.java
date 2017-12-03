package com.asaunin.classifier.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

@Getter
@EqualsAndHashCode
@ToString
public final class SubAccountCountryPair {

    private final Integer subAccountId;
    private final Country country;

    private SubAccountCountryPair(Integer subAccountId, Country country) {
        this.subAccountId = subAccountId;
        this.country = country;
    }

    public static SubAccountCountryPair of(Integer subAccountId) {
        return SubAccountCountryPair.of(subAccountId, AnyCountry.getInstance());
    }

    public static SubAccountCountryPair of(Integer subAccountId, Country country) {
        return new SubAccountCountryPair(subAccountId, country);
    }

    public boolean hasAnyCountry() {
        return Objects.equals(country, AnyCountry.getInstance());
    }

}
