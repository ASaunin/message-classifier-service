package com.asaunin.classifier.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class DeletableEntity implements BaseEntity {

    @Column(name = "deleted")
    private boolean deleted;

}
