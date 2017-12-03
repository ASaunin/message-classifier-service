package com.asaunin.classifier.domain;

import com.asaunin.cache.Deletable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = "deleted")
public class DeletableEntity implements BaseEntity, Deletable {

    private final boolean deleted;

}
