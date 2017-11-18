package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.DefaultPattern;
import com.asaunin.classifier.repository.DefaultPatternRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("dev")
public interface DefaultPatternH2Repository extends DefaultPatternRepository {

    Collection<DefaultPattern> findAll();

    @Query("SELECT id, subCategoryId, country, sender, regex, deleted FROM DefaultPattern WHERE updatedAt = :date")
    Collection<DefaultPattern> findByUpdatedAtAfter(String date);

}
