package com.asaunin.classifier.repository.h2;

import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@ContextConfiguration(classes = {
        SubCategoryH2Repository.class,
        RuleH2Repository.class,
        DefaultPatternH2Repository.class,
        CustomPatternH2Repository.class})
@JdbcTest
@TestPropertySource(properties = "spring.datasource.data:")
@IfProfileValue(name = "active.profile", value = "dev")
class H2RepositoryTestConfiguration {

}
