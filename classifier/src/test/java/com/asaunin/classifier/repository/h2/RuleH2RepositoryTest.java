package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.service.DataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class RuleH2RepositoryTest extends H2RepositoryTestConfiguration {

    private final AtomicInteger idCounter = new AtomicInteger(0);

    @Autowired private SubCategoryH2Repository subCategoryRepo;
    @Autowired private RuleH2Repository ruleRepo;

    private SubCategory subCategory;
    private Rule rule;

    private ZonedDateTime updatedAt;

    @Before
    public void setUp() {
        updatedAt = ZonedDateTime.now();
        subCategory = SubCategory.builder()
                .id(idCounter.incrementAndGet())
                .name("OTP")
                .category("TS")
                .build();
        subCategoryRepo.save(subCategory);

        rule = Rule.builder()
                .id(idCounter.incrementAndGet())
                .subCategoryId(subCategory.getId())
                .subAccountId(1)
                .country("RU")
                .build();
        ruleRepo.save(rule);
    }

    @Test
    public void findAll() {
        final Rule anotherRule = Rule.builder()
                .subCategoryId(subCategory.getId())
                .subAccountId(1)
                .country("RU")
                .build();
        ruleRepo.save(anotherRule);

        final Collection<Rule> list = ruleRepo.findAll();
        assertThat(list)
                .hasSize(2)
                .containsExactly(rule, anotherRule);
    }

    @Test
    public void findByUpdatedAtAfter() {
        Collection<Rule> list = ruleRepo.findByUpdatedAtAfter(updatedAt.minusDays(1));
        assertThat(list)
                .hasSize(1)
                .containsExactly(rule);

        list = ruleRepo.findByUpdatedAtAfter(ZonedDateTime.now());
        assertThat(list).isEmpty();
    }

}