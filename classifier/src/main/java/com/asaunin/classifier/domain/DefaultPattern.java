package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

@Value
@EqualsAndHashCode(of = {"id"}, callSuper = true)
public class DefaultPattern extends DeletableEntity implements TextMatcher, SenderMatcher {

    private Integer id;
    private Integer subCategoryId;
    private Country country;
    private Pattern sender;
    private Pattern text;
    private ZonedDateTime updatedAt;

    @Builder
    public DefaultPattern(Integer id, boolean deleted, Integer subCategoryId, String country, String sender, String text, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.country = Country.of(country);
        this.sender = Objects.nonNull(sender) ? Pattern.compile(sender) : null;
        this.text = Objects.nonNull(sender) ? Pattern.compile(text) : null;
        this.updatedAt = updatedAt;
    }

}
