package com.asaunin.classifier.repository;

import com.asaunin.classifier.domain.SubCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;

@Repository
@RequiredArgsConstructor
public abstract class SubCategoryRepository implements LoadableRepository<SubCategory> {

    protected final JdbcTemplate jdbcTemplate;
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Collection<SubCategory> findAll() {
        final String sql = "SELECT SubCategoryId, SubCategory, Category, Deleted, UpdatedAt " +
                "FROM cls.SubCategory";
        return jdbcTemplate.query(sql, this);
    }

    @Override
    public Collection<SubCategory> findByUpdatedAtAfter(ZonedDateTime dateTime) {
        final String sql = "SELECT SubCategoryId, SubCategory, Category, Deleted, UpdatedAt " +
                "FROM cls.SubCategory WHERE UpdatedAt > ?";
        return jdbcTemplate.query(sql, this, Date.from(dateTime.toInstant()));
    }

    @Override
    public void save(SubCategory entity) {
        final String sql =
                "MERGE INTO cls.SubCategory(SubCategoryId, SubCategory, Category, Deleted) " +
                        "VALUES (:SubCategoryId, :SubCategory, :Category, :Deleted)";

        namedParameterJdbcTemplate.update(sql, mapParams(entity));
    }

    @Nullable
    @Override
    public SubCategory mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SubCategory.builder()
                .id(rs.getInt("SubCategoryId"))
                .deleted(rs.getBoolean("Deleted"))
                .name(rs.getString("SubCategory"))
                .category(rs.getString("Category"))
                .updatedAt(hasColumn(rs, "UpdatedAt") ?
                        ZonedDateTime.ofInstant(
                                rs.getTimestamp("UpdatedAt").toInstant(),
                                ZoneId.systemDefault()) :
                        null)
                .build();
    }


    @Override
    public SqlParameterSource mapParams(SubCategory entity) {
        return new MapSqlParameterSource()
                .addValue("SubCategoryId", entity.getId())
                .addValue("SubCategory", entity.getName())
                .addValue("Category", entity.getCategory())
                .addValue("Deleted", entity.isDeleted());
    }

}
