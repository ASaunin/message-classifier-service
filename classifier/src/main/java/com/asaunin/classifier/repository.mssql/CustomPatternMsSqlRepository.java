package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.CustomPattern;
import com.asaunin.classifier.repository.CustomPatternRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("!dev")
public interface CustomPatternMsSqlRepository extends CustomPatternRepository {

    @Query(value = "EXEC cls.ClassificationPattern_Get", nativeQuery = true)
    Collection<CustomPattern> findAll();

    @Query(value = "EXEC cls.ClassificationPattern_Get", nativeQuery = true)
    Collection<CustomPattern> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
