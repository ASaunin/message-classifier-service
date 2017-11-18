package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.BaseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface LoadableRepository<T extends BaseEntity, ID> extends CrudRepository<T, ID> {

    Collection<T> findAll();

    Collection<T> findByUpdatedAtAfter(String date);

}
