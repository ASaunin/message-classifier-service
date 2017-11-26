package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

@Profile("!dev")
@Repository
public class RuleMsSqlRepository extends RuleRepository {

    public RuleMsSqlRepository(@Autowired JdbcTemplate jdbcTemplate,
                               @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public Collection<Rule> findAll() {
        final String sql = "EXEC cls.ClassificationRule_Get";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<Rule> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "EXEC cls.ClassificationRule_Get @LastSyncTimestamp = ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(Rule entity) {
        throw new UnsupportedOperationException("Stored procedure is not configured for this operation");
    }

}
