package com.asaunin.classifier.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class AnyCountry implements Country {

    private final static AnyCountry Instance = new AnyCountry();

    public static AnyCountry getInstance() {
        return Instance;
    }

    private AnyCountry() {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean isAnyCountry() {
        return true;
    }
}
