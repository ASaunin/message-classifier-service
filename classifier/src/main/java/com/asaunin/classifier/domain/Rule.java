package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@EqualsAndHashCode(exclude = {"id", "updatedAt"}, callSuper = true)
public class Rule extends DeletableEntity {

    private Integer id;
    private Integer subCategoryId;
    private Integer subAccountId;
    private String country;
    private ZonedDateTime updatedAt;

    @Builder
    public Rule(Integer id, boolean deleted, Integer subCategoryId, Integer subAccountId, String country, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.subCategoryId = subCategoryId;
        this.subAccountId = subAccountId;
        this.country = country;
        this.updatedAt = updatedAt;
    }

}
