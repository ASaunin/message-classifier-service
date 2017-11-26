package com.asaunin.classifier.repository.mssql;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureJdbc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = {
        SubCategoryMsSqlRepository.class,
        RuleMsSqlRepository.class,
        DefaultPatternMsSqlRepository.class,
        CustomPatternMsSqlRepository.class})
@Transactional
@AutoConfigureJdbc
@IfProfileValue(name = "active.profile", values = {"stage", "prod"})
class MsSqlRepositoryTestConfiguration {

}
