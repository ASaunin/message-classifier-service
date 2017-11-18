package com.asaunin.classifier.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = true)
public class Rule extends DeletableEntity {

    @Id
    @Column(name = "ruleid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "subcategoryid")
    private String categoryId;

    @Column(name = "subaccountuid")
    private Integer subAccountId;

    @Column(name = "country")
    private String country;

    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    @Transient
    private Date updatedAt;

}
