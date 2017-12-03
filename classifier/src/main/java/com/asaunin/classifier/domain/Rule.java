package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@EqualsAndHashCode(of = {"id"}, callSuper = true)
public class Rule extends DeletableEntity {

    private Integer id;
    private Integer subCategoryId;
    private Integer subAccountId;
    private Country country;
    private ZonedDateTime updatedAt;

    @Builder
    public Rule(Integer id, boolean deleted, Integer subCategoryId, Integer subAccountId, String country, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.subAccountId = subAccountId;
        this.country = Country.of(country);
        this.updatedAt = updatedAt;
    }

}
