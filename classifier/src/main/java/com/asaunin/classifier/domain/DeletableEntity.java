package com.asaunin.classifier.domain;

import com.asaunin.cache.Deletable;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class DeletableEntity implements BaseEntity, Deletable {

    @Column(name = "deleted")
    private boolean deleted;

}
