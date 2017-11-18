package com.asaunin.classifier.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@EqualsAndHashCode(of = "id", callSuper = true)
public class SubCategory extends DeletableEntity {

    @Id
    @Column(name = "subcategoryid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "subcategory")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "updatedat")
    @Temporal(TemporalType.TIMESTAMP)
    @Transient
    private Date updatedAt;

}
