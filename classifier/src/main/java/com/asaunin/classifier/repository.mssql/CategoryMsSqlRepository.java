package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.repository.CategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("!dev")
public interface CategoryMsSqlRepository extends CategoryRepository {

    @Query(value = "EXEC cls.Category_GetAll", nativeQuery = true)
    Collection<SubCategory> findAll();

    @Query(value = "EXEC cls.Category_GetAll", nativeQuery = true)
    Collection<SubCategory> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
