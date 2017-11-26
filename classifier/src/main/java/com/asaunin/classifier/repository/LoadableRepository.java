package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.BaseEntity;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;

@Repository
public interface LoadableRepository<T extends BaseEntity> extends RowMapper<T>, ParamSource<T> {

    Collection<T> findAll();

    Collection<T> findByUpdatedAtAfter(ZonedDateTime dateTime);

    void save(T entity);

}
