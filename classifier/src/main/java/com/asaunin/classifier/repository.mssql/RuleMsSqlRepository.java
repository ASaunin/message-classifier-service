package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.repository.RuleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("!dev")
public interface RuleMsSqlRepository extends RuleRepository {

    @Query(value = "EXEC cls.ClassificationRule_Get", nativeQuery = true)
    Collection<Rule> findAll();

    @Query(value = "EXEC cls.ClassificationRule_Get", nativeQuery = true)
    Collection<Rule> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
