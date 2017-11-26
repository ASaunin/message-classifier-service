package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.DefaultPattern;
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
public abstract class DefaultPatternRepository implements LoadableRepository<DefaultPattern> {

    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Collection<DefaultPattern> findAll() {
        final String sql = "SELECT PatternId, SubCategoryId, Country, SenderId, BodyPattern, Deleted, UpdatedAt " +
                "FROM cls.DefaultPattern";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<DefaultPattern> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "SELECT PatternId, SubCategoryId, Country, SenderId, BodyPattern, Deleted, UpdatedAt " +
                "FROM cls.DefaultPattern WHERE UpdatedAt > ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(DefaultPattern entity) {
        final String sql =
                "MERGE INTO cls.DefaultPattern(PatternId, SubCategoryId, Country, SenderId, BodyPattern, Deleted) " +
                        "VALUES (:PatternId, :SubCategoryId, :Country, :SenderId, :BodyPattern, :Deleted)";

        namedParameterJdbcTemplate.update(sql, mapParams(entity));
    }

    @Nullable
    @Override
    public DefaultPattern mapRow(ResultSet rs, int rowNum) throws SQLException {
        return DefaultPattern.builder()
                .id(rs.getInt("PatternId"))
                .deleted(rs.getBoolean("Deleted"))
                .subCategoryId(rs.getInt("SubCategoryId"))
                .country(rs.getString("Country"))
                .sender(rs.getString("SenderId"))
                .regex(rs.getString("BodyPattern"))
                .updatedAt(hasColumn(rs, "UpdatedAt") ?
                        ZonedDateTime.ofInstant(
                                rs.getTimestamp("UpdatedAt").toInstant(),
                                ZoneId.systemDefault()) :
                        null)
                .build();
    }

    @Override
    public SqlParameterSource mapParams(DefaultPattern entity) {
        return new MapSqlParameterSource()
                .addValue("PatternId", entity.getId())
                .addValue("Deleted", entity.isDeleted())
                .addValue("SubCategoryId", entity.getSubCategoryId())
                .addValue("Country", entity.getCountry())
                .addValue("SenderId", entity.getSender())
                .addValue("BodyPattern", entity.getRegex());
    }

}
