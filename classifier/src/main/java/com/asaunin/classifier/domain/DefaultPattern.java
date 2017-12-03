package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@EqualsAndHashCode(of = {"id"}, callSuper = true)
public class DefaultPattern extends DeletableEntity implements Pattern {

    private Integer id;
    private Integer subCategoryId;
    private Country country;
    private String sender;
    private String regex;
    private ZonedDateTime updatedAt;

    @Builder
    public DefaultPattern(Integer id, boolean deleted, Integer subCategoryId, String country, String sender, String regex, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.country = Country.of(country);
        this.sender = sender;
        this.regex = regex;
        this.updatedAt = updatedAt;
    }

}
