package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.CustomPattern;
import com.asaunin.classifier.repository.CustomPatternRepository;
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
public class CustomPatternMsSqlRepository extends CustomPatternRepository {

    public CustomPatternMsSqlRepository(@Autowired JdbcTemplate jdbcTemplate,
                                        @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public Collection<CustomPattern> findAll() {
        final String sql = "EXEC cls.ClassificationPattern_Get";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<CustomPattern> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "EXEC cls.ClassificationPattern_Get @LastSyncTimestamp = ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(CustomPattern entity) {
        throw new UnsupportedOperationException("Stored procedure is not configured for this operation");
    }

}
