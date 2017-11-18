package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.repository.RuleRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
@Profile("dev")
public interface RuleH2Repository extends RuleRepository {

    Collection<Rule> findAll();

    @Query("SELECT id, categoryId, subAccountId, country, deleted FROM Rule WHERE updatedAt = :date")
    Collection<Rule> findByUpdatedAtAfter(String date);

}
