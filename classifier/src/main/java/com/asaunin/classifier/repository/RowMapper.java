package com.asaunin.classifier.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface RowMapper<T> extends org.springframework.jdbc.core.RowMapper<T> {

    default boolean hasColumn(ResultSet rs, String columnName) {
        try
        {
            rs.findColumn(columnName);
            return true;
        } catch (SQLException ignored) {
        }
        return false;
    }

}
