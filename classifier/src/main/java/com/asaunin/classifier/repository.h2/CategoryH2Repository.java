package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.repository.CategoryRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("dev")
public interface CategoryH2Repository extends CategoryRepository {

    Collection<SubCategory> findAll();

    @Query("SELECT id, name, category, deleted FROM SubCategory WHERE updatedAt = :date")
    Collection<SubCategory> findByUpdatedAtAfter(String date);

}
