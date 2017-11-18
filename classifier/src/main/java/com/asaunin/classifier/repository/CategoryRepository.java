package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.SubCategory;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

@NoRepositoryBean
public interface CategoryRepository extends LoadableRepository<SubCategory, Integer> {

    Collection<SubCategory> findAll();

    Collection<SubCategory> findByUpdatedAtAfter(@Param("LastSyncTimeStamp") String date);

}
