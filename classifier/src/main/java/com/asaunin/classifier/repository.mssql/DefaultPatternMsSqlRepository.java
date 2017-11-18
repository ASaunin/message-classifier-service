package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.DefaultPattern;
import com.asaunin.classifier.repository.DefaultPatternRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("!dev")
public interface DefaultPatternMsSqlRepository extends DefaultPatternRepository {

    @Query(value = "EXEC cls.ClassificationPatternDefault_Get", nativeQuery = true)
    Collection<DefaultPattern> findAll();

    @Query(value = "EXEC cls.ClassificationPatternDefault_Get", nativeQuery = true)
    Collection<DefaultPattern> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
