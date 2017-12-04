package com.asaunin.classifier.domain;

import java.util.Objects;
import java.util.regex.Pattern;

@FunctionalInterface
interface TextMatcher {

    default boolean matchingByText(String text) {
        if (Objects.isNull(getText())) {
            return true;
        }

        final String regex = Objects.nonNull(text) ? text : "";
        return regex.matches(getText().pattern());
    }

    Pattern getText();

}
