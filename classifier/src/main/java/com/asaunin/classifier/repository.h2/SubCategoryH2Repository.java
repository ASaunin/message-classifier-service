package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Profile("dev")
@Repository
public class SubCategoryH2Repository extends SubCategoryRepository {

    public SubCategoryH2Repository(@Autowired JdbcTemplate jdbcTemplate,
                                   @Autowired NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

}
