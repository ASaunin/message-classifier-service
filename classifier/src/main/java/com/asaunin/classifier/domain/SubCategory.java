package com.asaunin.classifier.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.time.ZonedDateTime;

@Value
@EqualsAndHashCode(exclude = {"id", "updatedAt"}, callSuper = true)
public class SubCategory extends DeletableEntity {

    private Integer id;
    private String name;
    private String category;
    private ZonedDateTime updatedAt;

    @Builder
    public SubCategory(Integer id, boolean deleted, String name, String category, ZonedDateTime updatedAt) {
        super(deleted);
        this.id = id;
        this.name = name;
        this.category = category;
        this.updatedAt = updatedAt;
    }

}
