package com.asaunin.classifier.domain;

import org.junit.Test;

import java.util.regex.Pattern;

import static junit.framework.TestCase.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

public class PatternMatcherTest {

    @Test
    public void whenPatternIsNullThanAlwaysReturnTrue() {
        final CustomPattern customPattern = CustomPattern.builder()
                .sender(null)
                .text(null)
                .build();

        assertThat(customPattern.getText()).isNull();
        assertThat(customPattern.getSender()).isNull();

        assertTrue(customPattern.matchingByText(null));
        assertTrue(customPattern.matchingByText(""));
        assertTrue(customPattern.matchingByText(".*"));
        assertTrue(customPattern.matchingByText("ANY TEXT"));

        assertTrue(customPattern.matchingBySender(null));
        assertTrue(customPattern.matchingBySender(""));
        assertTrue(customPattern.matchingBySender(".*"));
        assertTrue(customPattern.matchingBySender("ANY SENDER"));
    }

    @Test
    public void whenTextIsNullThanReturnTrueIfMatchesEmptyString() {
        assertTrue(((TextMatcher) () -> Pattern.compile("Should match")).matchingByText("Should match"));
        assertTrue(((TextMatcher) () -> Pattern.compile("Should match.*")).matchingByText("Should match too"));
        assertFalse(((TextMatcher) () -> Pattern.compile("Should not match")).matchingByText("Should (.*){3} match"));
        assertTrue(((TextMatcher) () -> Pattern.compile("")).matchingByText(null));
        assertTrue(((TextMatcher) () -> Pattern.compile(".*")).matchingByText(null));
        assertTrue(((TextMatcher) () -> Pattern.compile(".*")).matchingByText(""));
        assertTrue(((TextMatcher) () -> Pattern.compile("(.*)")).matchingByText(""));
        assertFalse(((TextMatcher) () -> Pattern.compile("ANY PATTERN")).matchingByText(null));
    }


    @Test
    public void whenSenderIsNullThanReturnTrueIfMatchesEmptyString() {
        assertTrue(((SenderMatcher) () -> Pattern.compile("Should match")).matchingBySender("Should match"));
        assertTrue(((SenderMatcher) () -> Pattern.compile("Should match.*")).matchingBySender("Should match too"));
        assertFalse(((SenderMatcher) () -> Pattern.compile("Should not match")).matchingBySender("Should (.*){3} match"));
        assertFalse(((SenderMatcher) () -> Pattern.compile("")).matchingBySender(null)); //Differs !!!
        assertTrue(((SenderMatcher) () -> Pattern.compile(".*")).matchingBySender(null));
        assertTrue(((SenderMatcher) () -> Pattern.compile(".*")).matchingBySender(""));
        assertFalse(((SenderMatcher) () -> Pattern.compile("(.*)")).matchingBySender("")); //Differs !!!
        assertFalse(((SenderMatcher) () -> Pattern.compile("ANY PATTERN")).matchingBySender(null));
    }

}