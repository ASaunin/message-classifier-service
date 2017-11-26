package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@EqualsAndHashCode(exclude = {"id", "updatedAt"}, callSuper = true)
public class CustomPattern extends DeletableEntity {

    private Integer id;
    private Integer ruleId;
    private String sender;
    private String regex;
    private ZonedDateTime updatedAt;

    @Builder
    public CustomPattern(Integer id, boolean deleted, Integer ruleId, String sender, String regex, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.ruleId = ruleId;
        this.sender = sender;
        this.regex = regex;
        this.updatedAt = updatedAt;
    }

}
