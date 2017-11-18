package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.Rule;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

@NoRepositoryBean
public interface RuleRepository extends LoadableRepository<Rule, Integer> {

    Collection<Rule> findAll();

    Collection<Rule> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
