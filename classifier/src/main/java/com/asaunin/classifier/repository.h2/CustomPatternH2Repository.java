package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.CustomPattern;
import com.asaunin.classifier.repository.CustomPatternRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("dev")
public interface CustomPatternH2Repository extends CustomPatternRepository {

    Collection<CustomPattern> findAll();

    @Query("SELECT id, ruleId, sender, regex, deleted FROM CustomPattern WHERE updatedAt = :date")
    Collection<CustomPattern> findByUpdatedAtAfter(String date);

}
