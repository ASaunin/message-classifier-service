package com.asaunin.classifier.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = true)
public class DefaultPattern extends DeletableEntity {

    @Id
    @Column(name = "patternid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "subcategoryid")
    private Integer subCategoryId;

    @Column(name = "country")
    private String country;

    @Column(name = "senderid")
    private String sender;

    @Column(name = "bodypattern")
    private String regex;

    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    @Transient
    private Date updatedAt;

}
