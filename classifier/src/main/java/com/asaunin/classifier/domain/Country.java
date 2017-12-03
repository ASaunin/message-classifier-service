package com.asaunin.classifier.domain;

import java.util.Objects;

public interface Country {

    static Country of(String name) {
        if (Objects.nonNull(name) && !name.equals("")) {
            return new RealCountry(name.toUpperCase());
        }
        return AnyCountry.getInstance();
    }

    String getName();

    boolean isAnyCountry();

}
