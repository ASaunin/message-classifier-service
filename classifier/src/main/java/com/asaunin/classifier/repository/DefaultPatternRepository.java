package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.DefaultPattern;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@NoRepositoryBean
public interface DefaultPatternRepository extends LoadableRepository<DefaultPattern, Integer> {

    Collection<DefaultPattern> findAll();

    Collection<DefaultPattern> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
