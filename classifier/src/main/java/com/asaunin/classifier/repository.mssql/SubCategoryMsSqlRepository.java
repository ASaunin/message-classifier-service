package com.asaunin.classifier.repository.mssql;

import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.repository.SubCategoryRepository;
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
public class SubCategoryMsSqlRepository extends SubCategoryRepository {

    public SubCategoryMsSqlRepository(@Autowired JdbcTemplate jdbcTemplate,
                                      @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    public Collection<SubCategory> findAll() {
        final String sql = "EXEC cls.Category_GetAll";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<SubCategory> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "EXEC cls.Category_GetAll @LastSyncTimestamp = ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(SubCategory entity) {
        throw new UnsupportedOperationException("Stored procedure is not configured for this operation");
    }

}
