package com.asaunin.classifier.domain;

import com.asaunin.cache.Deletable;
import lombok.Data;

@Data
public class DeletableEntity implements BaseEntity, Deletable {

    private final boolean deleted;

}
