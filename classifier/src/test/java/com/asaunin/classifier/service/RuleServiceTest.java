package com.asaunin.classifier.service;

import com.asaunin.classifier.repository.mssql.RuleMsSqlRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = RuleService.class)
public class RuleServiceTest {

    @MockBean private RuleMsSqlRepository repository;
    @Autowired private RuleService service;

    @Before
    public void setUp() {
        service.clear();
    }

    @Test
    public void findBySubAccountId() {

    }

}