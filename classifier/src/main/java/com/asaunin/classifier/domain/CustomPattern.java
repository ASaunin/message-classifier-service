package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

@Value
@EqualsAndHashCode(of = {"id"}, callSuper = true)
public class CustomPattern extends DeletableEntity implements TextMatcher, SenderMatcher {

    private Integer id;
    private Integer ruleId;
    private Pattern sender;
    private Pattern text;
    private ZonedDateTime updatedAt;

    @Builder
    public CustomPattern(Integer id, boolean deleted, Integer ruleId, String sender, String text, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.ruleId = ruleId;
        this.sender = Objects.nonNull(sender) ? Pattern.compile(sender) : null;
        this.text = Objects.nonNull(sender) ? Pattern.compile(text) : null;
        this.updatedAt = updatedAt;
    }

}
