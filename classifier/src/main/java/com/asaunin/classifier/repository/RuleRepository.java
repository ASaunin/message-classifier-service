package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.RealCountry;
import com.asaunin.classifier.domain.Rule;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public abstract class RuleRepository implements LoadableRepository<Rule> {

    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Collection<Rule> findAll() {
        final String sql = "SELECT RuleId, SubCategoryId, SubAccountUid, Country, Deleted, UpdatedAt " +
                "FROM cls.Rule";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<Rule> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "SELECT RuleId, SubCategoryId, SubAccountUid, Country, Deleted, UpdatedAt " +
                "FROM cls.Rule WHERE UpdatedAt > ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(Rule entity) {
        final String sql =
                "MERGE INTO cls.Rule(RuleId, SubCategoryId, SubAccountUid, Country, Deleted) " +
                        "VALUES (:RuleId, :SubCategoryId, :SubAccountUid, :Country, :Deleted)";

        namedParameterJdbcTemplate.update(sql, mapParams(entity));
    }

    @Nullable
    @Override
    public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Rule.builder()
                .id(rs.getInt("RuleId"))
                .deleted(rs.getBoolean("Deleted"))
                .subCategoryId(rs.getInt("SubCategoryId"))
                .subAccountId(rs.getInt("SubAccountUid"))
                .country(rs.getString("Country"))
                .updatedAt(hasColumn(rs, "UpdatedAt") ?
                        ZonedDateTime.ofInstant(
                                rs.getTimestamp("UpdatedAt").toInstant(),
                                ZoneId.systemDefault()) :
                        null)
                .build();
    }

    @Override
    public SqlParameterSource mapParams(Rule entity) {
        return new MapSqlParameterSource()
                .addValue("RuleId", entity.getId())
                .addValue("Deleted", entity.isDeleted())
                .addValue("SubCategoryId", entity.getSubCategoryId())
                .addValue("SubAccountUid", entity.getSubAccountId())
                .addValue("Country", entity.getCountry().getName());
    }

}
