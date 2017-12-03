package com.asaunin.classifier.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode
@ToString
public class RealCountry implements Country {

    private final String name;

    RealCountry(String name) {
        this.name = name.toUpperCase();
    }

    @Override
    public boolean isAnyCountry() {
        return false;
    }

}
