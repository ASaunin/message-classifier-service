package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.CustomPattern;
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
public abstract class CustomPatternRepository implements LoadableRepository<CustomPattern> {

    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Collection<CustomPattern> findAll() {
        final String sql = "SELECT PatternId, RuleId, SenderId, BodyPattern, Deleted, UpdatedAt " +
                "FROM cls.CustomPattern";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<CustomPattern> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "SELECT PatternId, RuleId, SenderId, BodyPattern, Deleted, UpdatedAt " +
                "FROM cls.CustomPattern WHERE UpdatedAt > ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(CustomPattern entity) {
        final String sql =
                "MERGE INTO cls.CustomPattern(PatternId, RuleId, SenderId, BodyPattern, Deleted) " +
                        "VALUES (:PatternId, :RuleId, :SenderId, :BodyPattern, :Deleted)";

        namedParameterJdbcTemplate.update(sql, mapParams(entity));
    }

    @Nullable
    @Override
    public CustomPattern mapRow(ResultSet rs, int rowNum) throws SQLException {
        return CustomPattern.builder()
                .id(rs.getInt("PatternId"))
                .deleted(rs.getBoolean("Deleted"))
                .ruleId(rs.getInt("RuleId"))
                .sender(rs.getString("SenderId"))
                .text(rs.getString("BodyPattern"))
                .updatedAt(hasColumn(rs, "UpdatedAt") ?
                        ZonedDateTime.ofInstant(
                                rs.getTimestamp("UpdatedAt").toInstant(),
                                ZoneId.systemDefault()) :
                        null)
                .build();
    }


    @Override
    public SqlParameterSource mapParams(CustomPattern entity) {
        return new MapSqlParameterSource()
                .addValue("PatternId", entity.getId())
                .addValue("Deleted", entity.isDeleted())
                .addValue("RuleId", entity.getRuleId())
                .addValue("SenderId", entity.getSender())
                .addValue("BodyPattern", entity.getText());
    }

}
