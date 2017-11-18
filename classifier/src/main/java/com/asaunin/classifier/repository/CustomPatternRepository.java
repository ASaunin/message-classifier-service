package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.CustomPattern;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@NoRepositoryBean
public interface CustomPatternRepository extends LoadableRepository<CustomPattern, Integer> {

    Collection<CustomPattern> findAll();

    Collection<CustomPattern> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
