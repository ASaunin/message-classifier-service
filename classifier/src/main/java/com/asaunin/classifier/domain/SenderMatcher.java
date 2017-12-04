package com.asaunin.classifier.domain;

import java.util.Objects;
import java.util.regex.Pattern;

@FunctionalInterface
interface SenderMatcher {

    default boolean matchingBySender(String sender) {
        if (Objects.isNull(getSender())) {
            return true;
        }

        final String regex = Objects.nonNull(sender) ? sender : "";
        if (regex.equals("")) {
            return getSender().pattern().equals(".*");
        }

        return regex.matches(getSender().pattern());
    }

    Pattern getSender();

}
