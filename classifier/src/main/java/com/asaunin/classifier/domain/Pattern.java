package com.asaunin.classifier.domain;

import java.util.Objects;

public interface Pattern extends TextMatcher, SenderMatcher {

    // TODO: 02.12.2017 Add regexp validation

}

@FunctionalInterface
interface TextMatcher {

    default boolean matchingByText(String text) {
        if (Objects.isNull(getRegex())) {
            return true;
        }

        final String regex = Objects.nonNull(text) ? text : "";
        return regex.matches(getRegex());
    }

    String getRegex();

}

@FunctionalInterface
interface SenderMatcher {

    default boolean matchingBySender(String sender) {
        if (Objects.isNull(getSender())) {
            return true;
        }

        final String regex = Objects.nonNull(sender) ? sender : "";
        if (regex.equals("") && getSender().equals(".*")) {
            return true;
        }

        return regex.matches(getSender());
    }

    String getSender();

}
